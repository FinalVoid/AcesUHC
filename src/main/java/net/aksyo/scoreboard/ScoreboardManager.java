package net.aksyo.scoreboard;

import net.aksyo.AcesUHC;
import net.aksyo.scoreboard.providers.GameProvider;
import net.aksyo.scoreboard.providers.WaitProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardManager extends BukkitRunnable {

    private ScoreboardHandler sb;
    private String lines = "§7§m--------§7§m--------";
    private boolean dev = true;

    public ScoreboardManager(Plugin m, String t) {
        this.sb = new ScoreboardHandler(m, t);
    }

    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            ScoreboardHelper board = this.getSb().getScoreboard(player);
            board.clear();
            board.add(this.lines);
            for (String i : getProvider(player).getLines()) {
                board.add(i);
            }
            board.add(this.lines);
            board.add("§cDeveloppé par Aksyo");
            board.add("§cImaginé par 3 zigoto");
            board.update(player);

        }
    }

    private ScoreboardProvider getProvider(Player player) {
        switch(AcesUHC.getInstance().getGameManager().getCurrentGameState()){
            case WAITING:
                return new WaitProvider(player);
            default:
                return new GameProvider(player);
        }
    }

    public String getLines() {
        return this.lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public ScoreboardHandler getSb() {
        return this.sb;
    }

    public void setSb(ScoreboardHandler sb) {
        this.sb = sb;
    }

}


