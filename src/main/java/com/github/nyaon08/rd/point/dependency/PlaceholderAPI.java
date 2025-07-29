package com.github.nyaon08.rd.point.dependency;

import com.github.nyaon08.rd.point.SDPoint;
import com.github.nyaon08.rd.point.manager.PointManager;
import kr.rtuserver.framework.bukkit.api.integration.RSPlaceholder;
import org.bukkit.OfflinePlayer;

public class PlaceholderAPI extends RSPlaceholder<SDPoint> {

    private final PointManager pointManager;

    public PlaceholderAPI(SDPoint plugin) {
        super(plugin);
        this.pointManager = plugin.getPointManager();
    }

    @Override
    public String request(OfflinePlayer offlinePlayer, String[] params) {
        return switch (params[0]) {
            case "point" -> String.valueOf(pointManager.getPoint(offlinePlayer.getUniqueId()));
            default -> "ERROR";
        };
    }

}
