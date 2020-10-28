package net.aksyo.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BasicUtils {

    public static final void silentBroadcast(String message) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }

    }

    public static List<Player> getGameStartingPlayers(GameMode option) {

        List<Player> playersList = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == option) {
                playersList.add(player);
                continue;
            }
        }
        return playersList;
    }

}
