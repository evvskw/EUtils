package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Core;
import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CheckUpdateCommand implements CommandExecutor {

	private final String VERSION = Core.getInstance().getDescription().getVersion();

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
		if (!(sender instanceof Player p)) {
			Bukkit.getLogger().info("You must be a player to perform this command.");
			return true;
		}

		if (!p.hasPermission("eutils.command.checkupdate")) {
			p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
			return true;
		}

		try {
			final String latestVersion = Core.instance.latestVersion;
			if (latestVersion != null && !VERSION.equals(latestVersion)) {
				notifyPlayer(p, latestVersion);
				return true;
			}
			p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour EUtils version is up-to-date!"));
		} catch (final Exception e) {
			Bukkit.getLogger().severe("Error checking for updates: " + e.getMessage());
		}
		return true;

	}

	private void notifyPlayer(final Player player, final String latestVersion) {
		player.sendMessage(SC.CC("""
            &e&l------------------------
            &e&lE&f&lUtils &8- &a&lUPDATE FOUND
            &8- &aA new version is available! &7[&c""" + latestVersion + """
        &7]
        &8- &aDownload: https://github.com/evvskw/EUtils/releases/latest
        &e&l------------------------
        """));
	}

}