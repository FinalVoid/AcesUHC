package net.aksyo.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Preconditions;

public class ScoreboardHelper {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private List<ScoreboardText> list = new ArrayList();
    private Scoreboard scoreBoard;
    private Objective objective;
    private String tag = "PlaceHolder";
    private int lastSentCount = -1;

    public ScoreboardHelper(Scoreboard scoreBoard) {
        this.scoreBoard = scoreBoard;
        this.objective = getOrCreateObjective(this.tag);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public ScoreboardHelper(Scoreboard scoreBoard, String title) {
        Preconditions.checkState(title.length() <= 32, "title can not be more than 32");
        this.tag = ChatColor.translateAlternateColorCodes('&', title);
        this.scoreBoard = scoreBoard;
        this.objective = getOrCreateObjective(this.tag);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void add(String string) {
        string = ChatColor.translateAlternateColorCodes('&', string);
        ScoreboardText scoreboardInput = null;
        if (string.length() > 32) {
            string = string.substring(0, 32);
        }
        if (string.length() <= 16) {
            scoreboardInput = new ScoreboardText(string, "");
        } else {
            String string2 = string.substring(0, 16);
            String string3 = string.substring(16, string.length());
            if (string2.endsWith(String.valueOf('\u00a7'))) {
                string2 = string2.substring(0, string2.length() - 1);
                string3 = String.valueOf(String.valueOf('\u00a7')) + string3;
            }
            String string4 = ChatColor.getLastColors(string2);
            string3 = String.valueOf(String.valueOf(string4)) + string3;
            scoreboardInput = new ScoreboardText(string2, StringUtils.left(string3, 16));
        }
        this.list.add(scoreboardInput);
    }

    public void clear() {
        this.list.clear();
    }

    public void remove(int index) {
        String name = getNameForIndex(index);
        this.scoreBoard.resetScores(name);
        Team team = getOrCreateTeam(
                String.valueOf(String.valueOf(ChatColor.stripColor(StringUtils.left(this.tag, 14)))) + index, index);

        team.unregister();
    }

    public void update(Player player) {
        player.setScoreboard(this.scoreBoard);
        if (this.lastSentCount != -1) {
            int sentCount = this.list.size();
            int i = 0;
            while (i < this.lastSentCount - sentCount) {
                remove(sentCount + i);
                i++;
            }
        }
        int j = 0;
        while (j < this.list.size()) {
            Team team = getOrCreateTeam(
                    String.valueOf(String.valueOf(ChatColor.stripColor(StringUtils.left(this.tag, 14)))) + j, j);
            ScoreboardText str = (ScoreboardText) this.list.get(this.list.size() - j - 1);
            team.setPrefix(str.getLeft());
            team.setSuffix(str.getRight());
            this.objective.getScore(getNameForIndex(j)).setScore(j + 1);
            j++;
        }
        this.lastSentCount = this.list.size();
    }

    public Team getOrCreateTeam(String string, int n) {
        Team team = this.scoreBoard.getTeam(string);
        if (team == null) {
            team = this.scoreBoard.registerNewTeam(string);
            team.addEntry(getNameForIndex(n));
        }
        return team;
    }

    public Objective getOrCreateObjective(String string) {
        Objective objective = this.scoreBoard.getObjective("notoriuos");
        if (objective == null) {
            objective = this.scoreBoard.registerNewObjective("notoriuos", "dummy");
        }
        objective.setDisplayName(string);
        return objective;
    }

    public String getNameForIndex(int index) {
        return String.valueOf(String.valueOf(ChatColor.values()[index].toString())) + ChatColor.RESET;
    }

    public static class ScoreboardText {
        private String left;
        private String right;

        public ScoreboardText(String left, String right) {
            this.left = left;
            this.right = right;
        }

        public String getLeft() {
            return this.left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public String getRight() {
            return this.right;
        }

        public void setRight(String right) {
            this.right = right;
        }
    }
}

