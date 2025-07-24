package com.github.nyaon08.rtustudio.sd.command;

import com.github.nyaon08.rtustudio.sd.SDPoint;
import com.github.nyaon08.rtustudio.sd.manager.PointManager;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;
import kr.rtuserver.framework.bukkit.api.configuration.translation.message.MessageTranslation;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

public class CheckPointCommand extends RSCommand<SDPoint> {

    private final PointManager pointManager;

    public CheckPointCommand(SDPoint plugin) {
        super(plugin, "check", PermissionDefault.OP);
        this.pointManager = plugin.getPointManager();
    }

    @Override
    protected boolean execute(RSCommandData data) {
        Player target = provider().getPlayer(data.args(1));
        if (target == null) {
            chat().announce(message().getCommon(player(), String.valueOf(MessageTranslation.NOT_FOUND_ONLINE_PLAYER)));
            return true;
        }

        int currentPoint = pointManager.getPoint(target.getUniqueId());
        chat().announce(message().get(player(), "success.balance.other")
                .replace("[player]", provider().getName(target))
                .replace("[point]", String.valueOf(currentPoint))
        );

        return true;
    }

    @Override
    protected List<String> tabComplete(RSCommandData data) {
        if (data.length(2)) return provider().getNames();
        return List.of();
    }

}
