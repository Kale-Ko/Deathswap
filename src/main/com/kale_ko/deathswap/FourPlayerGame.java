package com.kale_ko.deathswap;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FourPlayerGame extends Game {
    Main plugin;

    public Player player1, player2, player3, player4;
    public Location player1Location, player2Location, player3Location, player4Location;

    public int time, taskId;

    @Override
    public Game start(List<Player> players, int gameTime) {
        this.time = gameTime;

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                time--;

                if (time == 0) {
                    plugin.sendGlobalMessage("Swap!");

                    swap();

                    time = plugin.config.getInt("time");
                } else if (time <= 10) {
                    plugin.sendGlobalMessage(Integer.toString(time));
                }
            }
        }, 0, 20);

        return this;
    }

    @Override
    public void swap() {
        player1Location = player1.getLocation();
        player2Location = player2.getLocation();
        player3Location = player3.getLocation();
        player3Location = player4.getLocation();

        double random = Math.floor(Math.random() * 2);

        if (random == 1) {
            player1.teleport(player2Location);
            player2.teleport(player3Location);
            player3.teleport(player4Location);
            player4.teleport(player1Location);
        } else if (random == 2) {
            player1.teleport(player3Location);
            player2.teleport(player4Location);
            player3.teleport(player1Location);
            player4.teleport(player2Location);
        } else {
            player1.teleport(player4Location);
            player2.teleport(player1Location);
            player3.teleport(player2Location);
            player4.teleport(player3Location);
        }
    }
}