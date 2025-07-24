package com.github.nyaon08.rtustudio.sd.command;

import com.github.nyaon08.rtustudio.sd.SDPoint;
import com.github.nyaon08.rtustudio.sd.manager.PointManager;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;
import kr.rtuserver.framework.bukkit.api.configuration.translation.message.MessageTranslation;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

public class AddPointCommand extends RSCommand<SDPoint> {

    private final PointManager pointManager;

    public AddPointCommand(SDPoint plugin) {
        super(plugin, "add", PermissionDefault.OP);
        this.pointManager = plugin.getPointManager();
    }

    @Override
    protected boolean execute(RSCommandData data) {
        Player target = provider().getPlayer(data.args(1));
        if (target == null) {
            chat().announce(message().getCommon(player(), String.valueOf(MessageTranslation.NOT_FOUND_ONLINE_PLAYER)));
            return true;
        }

        int point;
        try {
            point = Integer.parseInt(data.args(2));
        } catch (NumberFormatException e) {
            chat().announce(message().get(player(), "error.invalid-number"));
            return true;
        }

        pointManager.addPoint(target.getUniqueId(), point);
        chat().announce(message().get(player(), "success.added.sender")
                .replace("[player]", provider().getName(target))
                .replace("[point]", String.valueOf(point))
        );

        chat().announce(message().get(target, "success.added.receiver")
                .replace("[point]", String.valueOf(point))
        );

        return true;
    }

    @Override
    protected List<String> tabComplete(RSCommandData data) {
        if (data.length(2)) return provider().getNames();
        if (data.length(3)) return List.of(message().get(player(), "format.point"));
        return List.of();
    }

}
