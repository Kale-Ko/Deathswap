package com.kale_ko.deathswap;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Game {
    Main plugin;

    public int time, taskId;

    public Game start(List<Player> players, int gameTime) {
        this.time = gameTime;

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            time--;

            if (time == 0) {
                plugin.sendGlobalMessage("Swap!");

                swap();

                time = plugin.config.getInt("time");
            } else if (time <= 10) {
                plugin.sendGlobalMessage(Integer.toString(time));
            }
        }, 0, 20);

        return this;
    }

    public void swap() { }

    public void stop() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}