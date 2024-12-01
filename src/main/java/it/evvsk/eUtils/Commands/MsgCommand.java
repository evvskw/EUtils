package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {

	@Override
	public final boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

		if (!(sender instanceof Player p)) {
			Bukkit.getLogger().info("You must be a player to perform this command.");
			return true;
		}

		if (args.length >= 2) {

			Player subjectPlayer = Bukkit.getPlayer(args[0]);

			if (subjectPlayer == null) {
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInvalid user."));
				return true;
			}

			String message = String.join(" ", args).substring(args[0].length()).trim();

			subjectPlayer.sendMessage(SC.CC("From &e&l" + p.getName() + " &8» &f" + message));
			p.sendMessage(SC.CC("To &e&l" + subjectPlayer.getName() + " &8» &f" + message));

		} else {
			p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cCorrect usage: /msg <player> <message>"));
		}

		return true;
	}
}