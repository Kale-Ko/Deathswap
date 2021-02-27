package com.kale_ko.deathswap;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ThreePlayerGame extends Game {
    Main plugin;

    public Player player1;
    public Player player2;
    public Player player3;

    public Location player1Location;
    public Location player2Location;
    public Location player3Location;

    public int time;

    public int taskId;

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

        if (Math.floor(Math.random() * 2) == 1) {
            player1.teleport(player2Location);
            player2.teleport(player3Location);
            player3.teleport(player1Location);
        }  else {
            player1.teleport(player3Location);
            player2.teleport(player1Location);
            player3.teleport(player2Location);
        }
    }
}
