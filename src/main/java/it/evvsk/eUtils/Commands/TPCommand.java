package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class TPCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

		if (!(sender instanceof Player p)) {
			sender.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cYou must be a player to perform this command."));
			return true;
		}

		if (!p.hasPermission("eutils.command.teleport")) {
			sender.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
			return true;
		}

		if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
				return true;
			}
			p.teleport(target.getLocation());
			p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aTeleported to " + target.getName() + "."));
			return true;
		}

		if (args.length == 3) {
			try {
				double x = Double.parseDouble(args[0]);
				double y = Double.parseDouble(args[1]);
				double z = Double.parseDouble(args[2]);
				Location location = new Location(p.getWorld(), x, y, z);
				p.teleport(location);
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYou have been teleported to " + x + " " + y + " " + z + "."));
			} catch (NumberFormatException e) {
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInvalid coordinates. Use numbers with '.' as the decimal separator."));
			}
			return true;
		}

		if (args.length == 4) {
			try {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
					return true;
				}
				double x = Double.parseDouble(args[1]);
				double y = Double.parseDouble(args[2]);
				double z = Double.parseDouble(args[3]);
				Location location = new Location(p.getWorld(), x, y, z);
				target.teleport(location);
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aTeleported " + target.getName() + " to " + x + ", " + y + ", " + z + "."));
				target.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYou have been teleported to " + x + " " + y + " " + z + "."));
			} catch (NumberFormatException e) {
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInvalid coordinates. Use numbers with '.' as the decimal separator."));
			}
			return true;
		}

		p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cCorrect usage: /tp <target1/coordinates> <target2/coordinates>"));
		return true;
	}
}