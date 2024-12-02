package it.evvsk.eUtils.Commands;

import org.bukkit.Bukkit;
import it.evvsk.eUtils.Utils.SC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class BroadcastCommand implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

		final Player p = (Player) sender;

		if (!p.hasPermission("eutils.command.broadcast")) {
			p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
		} else {
			if (args.length < 1) {
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cCorrect usage: /broadcast <message>"));
			} else {
				final String message = String.join(" ", args);
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.sendMessage(SC.CC("&e&lBroad&f&lcast &8» &f" + message));
				}
			}
		}

		return true;
	}
}
