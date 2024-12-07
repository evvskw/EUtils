package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import it.evvsk.eUtils.Utils.SC;

public final class GodCommand implements CommandExecutor {

    private final Core instance = Core.getInstance();

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cYou must be a player to perform this command."));
            return true;
        }

        if (!p.hasPermission("eutils.command.god")) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
            return true;
        }

        if (args.length == 0) {
            if (instance.getGods().contains(p)) {
                instance.getGods().remove(p);
                p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cYou have now god mode disabled."));
                return true;
            }
            instance.getGods().add(p);
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYou have now god mode enabled."));
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
            return true;
        }

        if (instance.getGods().contains(target)) {
            instance.getGods().remove(target);
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &c" + target.getName() + " has now god mode disabled."));
            target.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cYou have now god mode disabled."));
            return true;
        }
        instance.getGods().add(target);
        p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + target.getName() + " has now god mode enabled."));
        target.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYou have now god mode enabled."));
        return true;
    }

}