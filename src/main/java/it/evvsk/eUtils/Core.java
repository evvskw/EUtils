package it.evvsk.eUtils;

import it.evvsk.eUtils.Commands.*;
import it.evvsk.eUtils.Events.GodEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;
    private static GodCommand godCommand;

    @Override
    public void onEnable() {

        instance = this;

        godCommand = new GodCommand();

        getCommand("god").setExecutor(godCommand);
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("gmc").setExecutor(new GMCreativeCommand());
        getCommand("gms").setExecutor(new GMSurvivalCommand());
        getCommand("gmsp").setExecutor(new GMSpectatorCommand());
        getCommand("gma").setExecutor(new GMAdventureCommand());
        getCommand("eutils").setExecutor(new MainCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("heal").setExecutor(new HealCommand());

        getServer().getPluginManager().registerEvents(new GodEvent(), this);

        getLogger().info("EUtils has started successfully.");

    }

    @Override
    public void onDisable() {

        getLogger().info("EUtils has stopped successfully. Goodbye!");

    }

    public static Core getInstance() {

        return instance;

    }

    public GodCommand getGodCommand() {

        return godCommand;

    }

}