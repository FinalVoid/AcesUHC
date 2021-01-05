package net.aksyo.utils;

import org.bukkit.ChatColor;

public enum LogFormat {

    DEBUG(ChatColor.RED + "[DEBUG] " + ChatColor.AQUA),
    ERROR(ChatColor.DARK_RED + "[ERROR] " + ChatColor.RED),
    INFO(ChatColor.BLUE + "[INFO] " + ChatColor.YELLOW),
    SUCCESS(ChatColor.DARK_GREEN + "[SUCCESS] " + ChatColor.GREEN);

    private String format;

    LogFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
