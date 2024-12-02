package it.evvsk.eUtils.commands;

import it.evvsk.eUtils.utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

        if (!(sender instanceof Player p)) {
            Bukkit.getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.fly")) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
            return true;
        }

        if (args.length == 0) {
            if (p.getAllowFlight()) {
                p.setAllowFlight(false);
                p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cFly is now disabled."));
                return true;
            }
            p.setAllowFlight(true);
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aFly is now enabled."));
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
            return true;
        }

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + target.getName() + " is not flying anymore."));
            target.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cFly is now disabled."));
            return true;
        }

        target.setAllowFlight(true);
        p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + target.getName() + " is now flying."));
        target.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aFly is now enabled."));

        return true;

    }

}