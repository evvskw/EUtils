package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class TPHereCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

		if (!(sender instanceof Player p)) {
			sender.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cYou must be a player to perform this command."));
			return true;
		}

		if (!p.hasPermission("eutils.command.tphere")) {
			sender.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
			return true;
		}

		if (args.length != 1) {
			p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cCorrect usage: /tphere <player>"));
			return true;
		}

		Player target;

		if ((target = Bukkit.getPlayer(args[0])) == null) {
			p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
			return true;
		}

		target.teleport(p.getLocation());
		p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aTeleported " + target.getName() + " to you."));
		target.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYou have been teleported to " + p.getName() + "."));
		return true;
	}
}