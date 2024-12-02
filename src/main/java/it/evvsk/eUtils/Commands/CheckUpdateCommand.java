package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Core;
import it.evvsk.eUtils.Utils.SC;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class CheckUpdateCommand implements CommandExecutor {

	private static final String VERSION = Core.getInstance().getDescription().getVersion();

	@Override
	public boolean onCommand(final org.bukkit.command.CommandSender sender, final org.bukkit.command.Command command, final String s, final String[] args) {

		Player p = (Player) sender;

		if (!(p.hasPermission("eutils.command.checkupdate"))) {

			p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));

			return true;

		}

		try {
			String latestVersion = Core.getInstance().getLatestVersion();

			if (latestVersion != null && !VERSION.equals(latestVersion)) {
				notifyPlayer(p, latestVersion);
			} else {
				p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour EUtils's version is up-to-date!"));
			}

		} catch (final Exception e) {
			Core.getInstance().getLogger().severe("Error checking for updates: " + e.getMessage());
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