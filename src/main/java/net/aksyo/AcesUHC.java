package net.aksyo;

import net.aksyo.command.CommandHandler;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.managers.WorldManager;
import net.aksyo.json.FileManager;
import net.aksyo.json.model.GameModel;
import net.aksyo.listeners.PlayerDamageListener;
import net.aksyo.listeners.PlayerDeathListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class AcesUHC extends JavaPlugin {

    private static AcesUHC instance;

    private PluginManager pluginManager = getServer().getPluginManager();

    public static String adminPrefix = "§c[ADMIN] ";
    public static String prefix = "§a[§3Aces UHC§a] ";

    private GameManager gameManager;
    private TeamManager teamManager;
    private WorldManager worldManager;

    private GameModel gameModel;

    private String path = getDataFolder().getPath();


    @Override
    public void onEnable() {
        instance = this;

        this.gameManager = new GameManager();
        this.teamManager = new TeamManager();
        this.worldManager = new WorldManager();

        registerCommands();
        registerEvents();

        try {
            gameModel = new FileManager(path).getGameModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void onDisable() {

    }

    public static final AcesUHC getInstance() {
        return instance;
    }

    public final String getPath() {
        return path;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public GameModel getGameModel() {
        return gameModel;
    }


    private final void registerCommands() {
        getCommand("ace").setExecutor(new CommandHandler());
    }

    private final void registerEvents() {
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PlayerDamageListener(), this);

    }

}
