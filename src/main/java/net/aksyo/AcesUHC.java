package net.aksyo;

import org.bukkit.plugin.java.JavaPlugin;

public class AcesUHC extends JavaPlugin {

    private static AcesUHC instance;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("Test");
    }

    @Override
    public void onDisable() {

    }

    public static final AcesUHC getInstance() {
        return instance;
    }
}
