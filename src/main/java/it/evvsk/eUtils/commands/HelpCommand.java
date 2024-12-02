package it.evvsk.eUtils.commands;

import it.evvsk.eUtils.utils.SC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public final class HelpCommand implements CommandExecutor {

    private final Plugin plugin;

    public HelpCommand(final Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        final StringBuilder helpMessage = new StringBuilder()
                .append(SC.CC("&e&l------------------------\n"))
                .append(SC.CC("&e&lE&f&lUtils\n"))
                .append(SC.CC("&8- &aA simple plugin with various utility commands.\n\n"))
                .append(SC.CC(""));

        final Map<String, Map<String, Object>> commands = plugin.getDescription().getCommands();

        if (commands != null && !commands.isEmpty()) {
            for (final Map.Entry<String, Map<String, Object>> entry : commands.entrySet()) {
                final String commandName = entry.getKey();
                final String description = (String) entry.getValue().getOrDefault("description", "");

                helpMessage.append(SC.CC("&e/"))
                        .append(commandName)
                        .append(SC.CC(" &8- &7"))
                        .append(description)
                        .append('\n');
            }
        } else {
            helpMessage.append(SC.CC("&cYou don't have access to any command.\n"));
        }

        helpMessage.append(SC.CC("&e&l------------------------"));

        sender.sendMessage(helpMessage.toString());

        return true;

    }

}