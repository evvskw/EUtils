package it.evvsk.eUtils.commands;

import it.evvsk.eUtils.utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (!(sender instanceof Player p)) {
            Bukkit.getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.feed")) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
            return true;
        }

        if (args.length == 0) {
            p.setHealth(20);
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour health has been filled successfully."));
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
            return true;
        }

        String message = target.getName().endsWith("s") ?
                "&e&lE&f&lUtils &8» &a" + target.getName() + "' health has been filled successfully." :
                "&e&lE&f&lUtils &8» &a" + target.getName() + "'s health has been filled successfully.";

        target.setHealth(20);
        target.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour health has been filled successfully."));
        p.sendMessage(SC.CC(message));
        return true;

    }

}
