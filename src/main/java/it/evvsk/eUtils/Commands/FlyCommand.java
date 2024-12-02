package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public final class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

        if (!(sender instanceof Player p)) {
            getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.fly")) {

            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));

        } else {

            if (args.length == 1) {

                final Player subjectPlayer;

                System.out.println((subjectPlayer = Bukkit.getPlayer(args[0])).isFlying());

                if (subjectPlayer.getAllowFlight() || subjectPlayer.isFlying()) {

                    subjectPlayer.setFlying(false);
                    subjectPlayer.setAllowFlight(false);

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + subjectPlayer.getName() + " is not flying anymore."));
                    subjectPlayer.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cFlying is now disabled."));

                } else {

                    subjectPlayer.setAllowFlight(true);
                    subjectPlayer.setFlying(true);

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + subjectPlayer.getName() + " is now flying."));
                    subjectPlayer.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aFlying is now enabled."));

                }

            } else {

                if (p.getAllowFlight() || p.isFlying()) {

                    p.setFlying(false);
                    p.setAllowFlight(false);

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cFlying is now disabled."));

                } else {

                    p.setAllowFlight(true);
                    p.setFlying(true);

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aFlying is now enabled."));

                }

            }

        }

        return true;

    }

}