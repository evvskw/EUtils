package it.evvsk.eUtils.Events;

import it.evvsk.eUtils.Core;
import it.evvsk.eUtils.Utils.SC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class PlayerJoinListener implements Listener {

	private static final String GITHUB_API_URL = "https://api.github.com/repos/evvskw/EUtils/releases/latest";
	private static final String VERSION = "1.3-SNAPSHOT";
	private static final String USER_AGENT = "Mozilla/5.0";

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		if (player.hasPermission("eutils.update.notify")) {
			try {
				final String latestVersion = fetchLatestVersion();
				if (latestVersion != null && !VERSION.equals(latestVersion)) {
					notifyPlayer(player, latestVersion);
				}
			} catch (final Exception e) {
				Core.getInstance().getLogger().severe("Error checking for updates: " + e.getMessage());
			}
		}
	}

	private String fetchLatestVersion() throws Exception {
		final HttpURLConnection connection = (HttpURLConnection) new URL(GITHUB_API_URL).openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

		final int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			final String response = readResponse(connection);
			return parseVersionFromResponse(response);
		} else {
			Core.getInstance().getLogger().warning("Update check failed. HTTP response code: " + responseCode);
			return null;
		}
	}

	private String readResponse(final HttpURLConnection connection) throws Exception {
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
			final StringBuilder response = new StringBuilder(1024);
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			return response.toString();
		}
	}

	private String parseVersionFromResponse(final String response) {
		final String tag = "\"tag_name\":\"";
		final int startIndex = response.indexOf(tag);
		if (startIndex != -1) {
			final int versionStart = startIndex + tag.length();
			final int versionEnd = response.indexOf('"', versionStart);
			if (versionEnd > versionStart) {
				return response.substring(versionStart, versionEnd);
			}
		}
		Core.getInstance().getLogger().warning("Failed to parse version: tag not found or invalid format.");
		return null;
	}

	private void notifyPlayer(final Player player, final String latestVersion) {
		player.sendMessage(SC.CC("""
            &e&l------------------------
            &e&lE&f&lUtils &8- &a&lUPDATE FOUND
            &8- &aA new version is available! &7[&c""" + latestVersion + """
        &7]
            \n&8- &aDownload: https://github.com/evvskw/EUtils/releases/latest
        &e&l------------------------
        """));
	}
}
