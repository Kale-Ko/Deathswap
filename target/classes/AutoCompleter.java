package com.kale_ko.api.spigot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class AutoCompleter implements TabCompleter {
    List<String> arguments = new ArrayList<String>();

    public AutoCompleter(List<String> list) {
        for (String string : list) {
            this.arguments.add(string);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], arguments, completions);
        Collections.sort(completions);
        return completions;
    }
}
