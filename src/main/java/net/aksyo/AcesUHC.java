package net.aksyo;

import net.aksyo.command.CommandHandler;
import net.aksyo.game.managers.ChestManager;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.managers.WorldManager;
import net.aksyo.json.FileManager;
import net.aksyo.json.model.GameModel;
import net.aksyo.listeners.*;
import net.aksyo.scoreboard.ScoreboardHandler;
import net.aksyo.scoreboard.ScoreboardManager;
import net.aksyo.tab.TabManager;
import net.aksyo.utils.LogFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
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
    private ChestManager chestManager;
    private ScoreboardManager scoreboardManager;
    private TabManager tabManager;

    private GameModel gameModel;

    private String path = getDataFolder().getPath();


    @Override
    public void onEnable() {
        instance = this;

        this.gameManager = new GameManager();
        this.teamManager = new TeamManager();
        this.worldManager = new WorldManager(getServer().getWorlds().get(0));
        this.chestManager = new ChestManager(100);
        this.scoreboardManager = new ScoreboardManager(this, "§3ACES UHC");
        tabManager = new TabManager();
        scoreboardManager.runTaskTimerAsynchronously(this, 0, 20);

        registerCommands();
        registerEvents();

        try {
            gameModel = new FileManager(path).getGameModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        worldManager.world.setGameRuleValue("keepInventory ", "true");
        worldManager.world.setGameRuleValue("naturalRegeneration", "false");
        worldManager.world.setDifficulty(Difficulty.EASY);


    }

    @Override
    public void onDisable() {

    }

    public void log(LogFormat format, String... args) {
        for (String log : args) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[ACES UHC] " + format.getFormat() + log);
        }
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

    public ChestManager getChestManager() {
        return chestManager;
    }

    public TabManager getTabManager() { return tabManager; }

    public GameModel getGameModel() {
        return gameModel;
    }


    private final void registerCommands() {
        getCommand("ace").setExecutor(new CommandHandler());
    }

    private final void registerEvents() {
        pluginManager.registerEvents(new PlayerGlobalListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PlayerDamageListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new PlayerFoodListener(), this);
        pluginManager.registerEvents(new ScenariosListener(), this);

    }

}
