package com.github.nyaon08.rd.point.command;

import com.github.nyaon08.rd.point.SDPoint;
import com.github.nyaon08.rd.point.manager.PointManager;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;

public class MainCommand extends RSCommand<SDPoint> {

    private final PointManager pointManager;

    public MainCommand(SDPoint plugin) {
        super(plugin, "point");

        this.pointManager = plugin.getPointManager();

        registerCommand(new CheckPointCommand(plugin));
        registerCommand(new AddPointCommand(plugin));
    }

    @Override
    protected boolean execute(RSCommandData data) {
        int currentPoint = pointManager.getPoint(player().getUniqueId());
        chat().announce(message().get(player(), "success.balance.self")
                .replace("[point]", String.valueOf(currentPoint))
        );

        return true;
    }

    @Override
    protected void reload(RSCommandData data) {
    }

}
