package com.github.nyaon08.rtustudio.sd.manager;

import com.github.nyaon08.rtustudio.sd.SDPoint;
import kr.rtuserver.framework.bukkit.api.platform.JSON;
import kr.rtuserver.framework.bukkit.api.storage.Storage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class PointManager {

    private final SDPoint plugin;

    @Getter
    private final Map<UUID, Integer> points = new HashMap<>();

    public void initPlayer(UUID uuid) {
        Storage storage = plugin.getStorage();
        storage.get("points", JSON.of("uuid", uuid.toString())).thenAccept(result -> {
            if (result.isEmpty() || result.getFirst().isJsonNull()) {
                storage.add("points", JSON.of("uuid", uuid.toString()).append("point", 0));
            }
        });
    }

    public void addPoint(UUID uuid, int point) {
        Storage storage = plugin.getStorage();
        storage.get("points", JSON.of("uuid", uuid.toString())).thenAccept(result -> {
            int currentPoint = result.getFirst().get("point").getAsInt();
            storage.set("points", JSON.of("uuid", uuid.toString()), JSON.of("point", currentPoint + point));
        });

        points.remove(uuid);
    }

    public int getPoint(UUID uuid) {
        if (points.containsKey(uuid)) return points.get(uuid);
        Storage storage = plugin.getStorage();
        return storage.get("points", JSON.of("uuid", uuid.toString())).thenApply(result -> {
            if (result.isEmpty() || result.getFirst().isJsonNull()) return 0;
            int point = result.getFirst().get("point").getAsInt();
            points.put(uuid, point);
            return point;
        }).join();
    }

}
