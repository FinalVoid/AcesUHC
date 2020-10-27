package net.aksyo.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BasicUtils {

    public static final void silentBroadcast(String message) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }

    }

}
