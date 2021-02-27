package com.kale_ko.api.spigot;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class TextStyler {
    public static String style(String text, FileConfiguration config) {
        return ChatColor.translateAlternateColorCodes('&', config.getString("prefix") + " &r" + config.getString("default-message-color") + text);
    }

    public static String noPrefix(String text, FileConfiguration config) {
        return ChatColor.translateAlternateColorCodes('&', config.getString("default-message-color") + text);
    }

    public static String noExtra(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}