package com.github.nyaon08.rtustudio.sd.listener;

import com.github.nyaon08.rtustudio.sd.SDPoint;
import com.github.nyaon08.rtustudio.sd.manager.PointManager;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerConnectionListener extends RSListener<SDPoint> {

    private final PointManager pointManager;

    public PlayerConnectionListener(SDPoint plugin) {
        super(plugin);
        this.pointManager = plugin.getPointManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        pointManager.initPlayer(uuid);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
    }

}
