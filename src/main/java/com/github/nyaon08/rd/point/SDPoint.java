package com.github.nyaon08.rd.point;

import com.github.nyaon08.rd.point.command.MainCommand;
import com.github.nyaon08.rd.point.dependency.PlaceholderAPI;
import com.github.nyaon08.rd.point.listener.PlayerConnectionListener;
import com.github.nyaon08.rd.point.manager.PointManager;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import lombok.Getter;

public class SDPoint extends RSPlugin {

    @Getter
    private static SDPoint instance;

    @Getter
    private PointManager pointManager;

    private PlaceholderAPI placeholder;

    @Override
    public void load() {
        instance = this;
    }

    @Override
    public void enable() {
        getConfigurations().getStorage().init("points");

        pointManager = new PointManager(this);

        registerCommand(new MainCommand(this), true);
        registerEvent(new PlayerConnectionListener(this));

        if (getFramework().isEnabledDependency("PlaceholderAPI")) {
            placeholder = new PlaceholderAPI(this);
            placeholder.register();
        }
    }

    @Override
    public void disable() {
        if (getFramework().isEnabledDependency("PlaceholderAPI")) {
            placeholder.unregister();
        }
    }

}
