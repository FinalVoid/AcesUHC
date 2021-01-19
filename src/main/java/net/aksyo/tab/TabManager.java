package net.aksyo.tab;

import net.aksyo.game.roles.ITeam;
import net.aksyo.game.teams.*;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TabManager {

    private HashMap<ITeam, String> teamTabDesign;

    public TabManager() {
        teamTabDesign = new HashMap<>();
        teamTabDesign.put(JokerTeam.getInstance(), "§l§7J§r");
        teamTabDesign.put(PiquesTeam.getInstance(), "§l§9♠§r");
        teamTabDesign.put(TrefleTeam.getInstance(), "§l§9♣§r");
        teamTabDesign.put(CoeurTeam.getInstance(), "§l§c♥§r");
        teamTabDesign.put(CarreauxTeam.getInstance(), "§l§c♦§r");

    }

    public void setAcesPlayerTabName(AcePlayer[] acePlayers) {

        for (AcePlayer acePlayer : acePlayers) {

            Player player = acePlayer.getPlayer();
            player.setPlayerListName(teamTabDesign.get(acePlayer.getTeam()) + " §6" + player.getName());

        }

    }


}
