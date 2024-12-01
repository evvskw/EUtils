package it.evvsk.eUtils.Events;

import it.evvsk.eUtils.Core;
import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlayerJoinListener implements Listener {

	private static final String GITHUB_API_URL = "https://api.github.com/repos/evvskw/EUtils/releases/latest";
	private static final String VERSION = "1.3-SNAPSHOT";

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission("eutils.update.notify")) {
			Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
				try {
					HttpURLConnection connection = (HttpURLConnection) new URL(GITHUB_API_URL).openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

					if (connection.getResponseCode() == 200) {
						BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						StringBuilder response = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {
							response.append(line);
						}
						reader.close();

						String latestVersion = parseVersionFromResponse(response.toString());
						if (!VERSION.equals(latestVersion)) {
							notifyPlayer(player, latestVersion);
						}
					} else {
						Core.getInstance().getLogger().warning("Failed to check for updates. Response code: " + connection.getResponseCode());
					}
				} catch (Exception e) {
					Core.getInstance().getLogger().severe("An error occurred while checking for updates: " + e.getMessage());
				}
			});
		}
	}

	private String parseVersionFromResponse(String response) {
		try {
			String tag = "\"tag_name\":\"";
			int startIndex = response.indexOf(tag) + tag.length();
			int endIndex = response.indexOf("\"", startIndex);
			return response.substring(startIndex, endIndex);
		} catch (Exception e) {
			Core.getInstance().getLogger().warning("Failed to parse version from response: " + e.getMessage());
			return VERSION;
		}
	}

	private void notifyPlayer(Player player, String latestVersion) {
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