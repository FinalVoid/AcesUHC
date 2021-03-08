package net.aksyo.scoreboard.providers;

import net.aksyo.scoreboard.ScoreboardProvider;
import net.aksyo.utils.BasicUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WaitProvider extends ScoreboardProvider {

    private Player player;

    public WaitProvider(Player player) {
        this.player = player;
    }

    @Override
    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        lines.add(" ");
        lines.add(ChatColor.YELLOW + "Players : §9" + BasicUtils.getGameStartingPlayers(GameMode.ADVENTURE).size());
        lines.add(" ");
        return lines;
    }

    public Player getPlayer() {
        return player;
    }

}
