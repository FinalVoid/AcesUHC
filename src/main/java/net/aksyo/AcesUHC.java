package net.aksyo;

import net.aksyo.game.managers.GameManager;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.managers.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AcesUHC extends JavaPlugin {

    private static AcesUHC instance;

    private GameManager gameManager;
    private TeamManager teamManager;
    private WorldManager worldManager;

    @Override
    public void onEnable() {
        instance = this;

        this.gameManager = new GameManager();
        this.teamManager = new TeamManager();
        this.worldManager = new WorldManager();

    }

    @Override
    public void onDisable() {

    }

    public static final AcesUHC getInstance() {
        return instance;
    }
}