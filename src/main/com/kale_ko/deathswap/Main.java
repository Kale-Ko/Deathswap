package com.kale_ko.deathswap;

import com.kale_ko.api.spigot.AutoCompleter;
import com.kale_ko.api.spigot.TextStyler;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    public String version = "1.0.0";

    public FileConfiguration config = this.getConfig();

    Game game;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        saveConfig();
        config = this.getConfig();

        List<String> subcommands = new ArrayList<String>();
        subcommands.add("help");
        subcommands.add("start");
        subcommands.add("stop");
        subcommands.add("reload");
        this.getCommand("deathswap").setTabCompleter(new AutoCompleter(subcommands));

        log("Enabled");
    }

    @Override
    public void onDisable() {
        log("Disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("deathswap")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("help")) {
                    sendMessage(sender, "Type /deathswap help for this");
                    sendMessage(sender, "Type /deathswap start to start the game");
                    sendMessage(sender, "Type /deathswap reload to reload the config");

                    return true;
                } else if (args[0].equalsIgnoreCase("reload")) {
                    sendMessage(sender, config.getString("messages.reload"));

                    this.reloadConfig();
                    config = this.getConfig();

                    return true;
                } else if (args[0].equalsIgnoreCase("start")) {
                    if (Bukkit.getOnlinePlayers().size() >= 2 && Bukkit.getOnlinePlayers().size() <= 4 ) {
                        List<Player> players = new ArrayList<Player>();

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            players.add(player);
                        }

                        if (players.size() == 2) {
                            game = new TwoPlayerGame().start(players, config.getInt("time"));
                        } else if (players.size() == 3) {
                            game = new ThreePlayerGame().start(players, config.getInt("time"));
                        } else if (players.size() == 4) {
                            game = new FourPlayerGame().start(players, config.getInt("time"));
                        }

                        sendGlobalMessage(config.getString("messages.starting"));
                    } else {
                        sendMessage(sender, config.getString("messages.must-have-two-people"));
                    }

                    return true;
                } else if (args[0].equalsIgnoreCase("stop")) {
                    game.stop();

                    sendGlobalMessage(config.getString("messages.stopping"));

                    return true;
                } else {
                    sendMessage(sender, config.getString("messages.not-a-command"));

                    return true;
                }
            } else {
                sendMessage(sender, config.getString("messages.not-a-command"));

                return true;
            }
        }

        return false;
    }

    public void isConsole(CommandSender sender) {
        sendMessage(sender, config.getString("messages.no-console"));
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(TextStyler.style(message, config));
    }

    public void sendGlobalMessage(String message) {
        getServer().broadcastMessage(TextStyler.style(message, config));
    }

    public void log(String message) {
        getLogger().info(message);
    }

    public void warn(String message) {
        getLogger().warning(message);
    }

    public void error(String message) {
        getLogger().severe(message);
    }
}