package it.evvsk.eUtils.Listeners;

import it.evvsk.eUtils.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.Map;

public final class PlayerChatListener implements Listener {

	@EventHandler
	public void onMessage(final AsyncPlayerChatEvent event) {

		final Map<String, Object> emojiMap;

		if ((emojiMap = Core.getInstance().getDataMap()) != null && emojiMap.containsKey("emojis")) {

			Core.getInstance().getLogger().info(event.getMessage());

			String message = event.getMessage();

			for (Map.Entry<String, String> entry : ((Map<String, String>) emojiMap.get("emojis")).entrySet()) {

				final String wordToReplace = entry.getKey();
				final String emojiReplacement = entry.getValue();

				message = message.replaceAll("(?i)" + wordToReplace, emojiReplacement);

			}

			event.setMessage(message);

		}
	}
}