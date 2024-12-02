package it.evvsk.eUtils.commands;

import it.evvsk.eUtils.utils.SC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class PancakeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
		sender.sendMessage(SC.CC("&e&lE&f&lUtils &8» &6Pancake!"));
		return true;
	}

}
