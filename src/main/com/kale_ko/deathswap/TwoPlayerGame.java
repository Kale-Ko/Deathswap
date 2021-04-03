package com.kale_ko.deathswap;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TwoPlayerGame extends Game {
    Main plugin;

    public Player player1, player2;
    public Location player1Location, player2Location;

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

        player1.teleport(player2Location);
        player2.teleport(player1Location);
    }
}