package net.aksyo.scoreboard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardHandler implements Listener {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map<UUID, ScoreboardHelper> playerboardHelper = new HashMap();
    private Plugin plugin;
    private String title;

    public ScoreboardHandler(Plugin m, String title) {
        Bukkit.getPluginManager().registerEvents(this, m);
        Collection<? extends Player> arrplayer = Bukkit.getOnlinePlayers();
        this.plugin = m;
        this.title = title;
        for (Player i : arrplayer) {
            handleScoreboard(i);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        handleScoreboard(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        this.playerboardHelper.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.playerboardHelper.remove(event.getPlayer().getUniqueId());
    }

    void handleScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(board);
        ScoreboardHelper helper = new ScoreboardHelper(board, this.title);
        this.playerboardHelper.put(player.getUniqueId(), helper);

    }

    public ScoreboardHelper getScoreboard(Player player) {
        return (ScoreboardHelper) this.playerboardHelper.get(player.getUniqueId());
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
}
