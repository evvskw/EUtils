package it.evvsk.eUtils.commands;

import it.evvsk.eUtils.utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        if (!(sender instanceof Player p)) {
            Bukkit.getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.feed")) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &cInsufficient permissions."));
            return true;
        }

        if (args.length == 0) {
            p.setFoodLevel(20);
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &aYour saturation has been filled successfully."));
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
            return true;
        }

        target.setFoodLevel(20);

        final String saturationMsg = target.getName().endsWith("s") ?
                "&e&lE&f&lUtils &8&l» &a" + target.getName() + "' saturation has been filled successfully." :
                "&e&lE&f&lUtils &8&l» &a" + target.getName() + "'s saturation has been filled successfully.";

        p.sendMessage(SC.CC(saturationMsg));
        target.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &aYour saturation has been filled successfully."));

        return true;

    }

}
