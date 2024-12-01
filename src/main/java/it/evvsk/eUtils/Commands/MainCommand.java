package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] strings) {

        sender.sendMessage(
                SC.CC("""
                        &e&l------------------------
                        &e&lE&f&lUtils
                        &8- &aA simple plugin with various utility commands.
                        
                        &cBy evvsk!
                        &e&l------------------------
                        """));

        return true;

    }

}