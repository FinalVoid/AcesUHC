package net.aksyo.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BasicUtils {

    public static final void silentBroadcast(String message) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }

    }

    public static final void playSoundToAllPlayer(Sound sound) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, 1f, 1f);
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
