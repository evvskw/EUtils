package it.evvsk.eUtils.Events;

import it.evvsk.eUtils.Core;
import it.evvsk.eUtils.Utils.SC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class PlayerJoinListener implements Listener {

	private static final String VERSION = Core.getInstance().getDescription().getVersion();

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();

		if (player.hasPermission("eutils.update.notify")) {
			try {
				String latestVersion = Core.getInstance().getLatestVersion();

				if (latestVersion != null && !VERSION.equals(latestVersion)) {
					notifyPlayer(player, latestVersion);
				}

			} catch (final Exception e) {
				Core.getInstance().getLogger().severe("Error checking for updates: " + e.getMessage());
			}
		}
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