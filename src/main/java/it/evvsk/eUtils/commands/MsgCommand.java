package it.evvsk.eUtils.commands;

import it.evvsk.eUtils.Core;
import it.evvsk.eUtils.utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class MsgCommand implements CommandExecutor {

	private final Core core = Core.getInstance();

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

		if (!(sender instanceof Player p)) {
			Bukkit.getLogger().info("You must be a player to perform this command.");
			return true;
		}

		if (args.length > 1) {
			final Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInvalid user."));
				return true;
			}
			final String message = String.join(" ", args).substring(args[0].length()).trim();

			target.sendMessage(SC.CC("From &e&l" + p.getName() + " &8» &f" + message));
			p.sendMessage(SC.CC("To &e&l" + target.getName() + " &8» &f" + message));
			core.getLastMessagedPlayerList().remove(p);
			core.getLastMessagedPlayerList().remove(target);
			core.getLastMessagedPlayerList().put(p, target);
			core.getLastMessagedPlayerList().put(target, p);
			return true;
		}
		p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cCorrect usage: /msg <player> <message>"));
		return true;
	}
}