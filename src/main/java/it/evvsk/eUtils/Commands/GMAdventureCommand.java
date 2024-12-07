package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class GMAdventureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (!(sender instanceof Player p)) {
            Bukkit.getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.gamemode.adventure")) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
            return true;
        }

        if (args.length == 0) {
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour gamemode has been changed successfully to adventure."));
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
            return true;
        }

        String message = target.getName().endsWith("s") ?
                "&e&lE&f&lUtils &8» &a" + target.getName() + "' gamemode has been changed successfully to adventure." :
                "&e&lE&f&lUtils &8» &a" + target.getName() + "'s gamemode has been changed successfully to adventure.";
        target.setGameMode(GameMode.ADVENTURE);
        p.sendMessage(SC.CC(message));
        target.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour gamemode has been changed successfully to adventure."));
        return true;
    }

}