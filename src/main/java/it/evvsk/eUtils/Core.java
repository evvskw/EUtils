package it.evvsk.eUtils;

import it.evvsk.eUtils.Commands.*;
import it.evvsk.eUtils.Events.EntityDamageListener;
import it.evvsk.eUtils.Events.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;
    private static GodCommand godCommand;
    private MsgCommand msgCommand;

    @Override
    public void onEnable() {
        instance = this;

        godCommand = new GodCommand();
        msgCommand = new MsgCommand();

        getCommand("god").setExecutor(godCommand);
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("gmc").setExecutor(new GMCreativeCommand());
        getCommand("gms").setExecutor(new GMSurvivalCommand());
        getCommand("gmsp").setExecutor(new GMSpectatorCommand());
        getCommand("gma").setExecutor(new GMAdventureCommand());
        getCommand("eutils").setExecutor(new HelpCommand(this));
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("msg").setExecutor(msgCommand);
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("r").setExecutor(new ResponseCommand());
        getCommand("pancake").setExecutor(new PancakeCommand());

        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

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

    public MsgCommand getMsgCommand() {
        return msgCommand;
    }
}