package it.evvsk.eUtils;

import it.evvsk.eUtils.commands.*;
import it.evvsk.eUtils.listeners.EntityDamageListener;
import it.evvsk.eUtils.listeners.PlayerJoinListener;
import it.evvsk.eUtils.utils.UpdateChecker;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public final class Core extends JavaPlugin {

    @Getter private static Core instance;
    private List<Player> gods;
    private String latestVersion;
    private Map<Player, Player> lastMessagedPlayerList;

    @Override
    public void onEnable() {
        instance = this;
        gods = new ArrayList<>();
        lastMessagedPlayerList = new HashMap<>();

        checkForUpdates();

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

        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

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
}