package it.evvsk.eUtils;

import it.evvsk.eUtils.Commands.*;
import it.evvsk.eUtils.Listeners.EntityDamageListener;
import it.evvsk.eUtils.Listeners.PlayerChatListener;
import it.evvsk.eUtils.Listeners.PlayerJoinListener;
import it.evvsk.eUtils.Utils.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Getter
public final class Core extends JavaPlugin {

	@Getter
	public static Core instance;

	private List<Player> gods;
	public String latestVersion;
	private Map<Player, Player> lastMessagedPlayerList;

	@Getter private Map<String, Object> dataMap;

	@Override
	public void onEnable() {
		instance = this;
		gods = new ArrayList<>();
		lastMessagedPlayerList = new HashMap<>();

		checkForUpdates();
		loadJsonData();

		getCommand("god").setExecutor(new GodCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("gmc").setExecutor(new GMCreativeCommand());
		getCommand("gms").setExecutor(new GMSurvivalCommand());
		getCommand("gmsp").setExecutor(new GMSpectatorCommand());
		getCommand("gma").setExecutor(new GMAdventureCommand());
		getCommand("eutils").setExecutor(new HelpCommand(this));
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("msg").setExecutor(new MsgCommand());
		getCommand("broadcast").setExecutor(new BroadcastCommand());
		getCommand("r").setExecutor(new ResponseCommand());
		getCommand("pancake").setExecutor(new PancakeCommand());
		getCommand("checkupdate").setExecutor(new CheckUpdateCommand());
		getCommand("tp").setExecutor(new TPCommand());
		getCommand("tphere").setExecutor(new TPHereCommand());

		getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);

		getLogger().info("EUtils has started successfully.");
	}

	@Override
	public void onDisable() {
		getLogger().info("EUtils has stopped successfully. Goodbye!");
	}

	private void checkForUpdates() {
		try {
			UpdateChecker updateChecker = new UpdateChecker();
			latestVersion = updateChecker.fetchLatestVersion();

			if (latestVersion != null) {
				getLogger().info("Latest version found: " + latestVersion);
			} else {
				getLogger().warning("Unable to fetch the latest version.");
			}
		} catch (Exception e) {
			getLogger().severe("An error occurred while checking for updates: " + e.getMessage());
		}
	}

	private void loadJsonData() {
		String defaultJson = "{ \"emojis\": { \"star\": \"⭐\", \"stella\": \"⭐\", \"<3\": \"❤\", \"heart\": \"❤\" } }";

		try {
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, Object>>() {}.getType();

			dataMap = gson.fromJson(defaultJson, type);

			getLogger().info("Loaded JSON data from string: " + dataMap);
		} catch (Exception e) {
			getLogger().severe("Failed to load JSON data from string: " + e.getMessage());
		}
	}
}