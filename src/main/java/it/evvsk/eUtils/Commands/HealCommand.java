package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public final class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

        if (!(sender instanceof Player p)) {
            getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.feed")) {

            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));

        } else {

            if (args.length == 1) {

                final Player subjectPlayer;

                if ((subjectPlayer = Bukkit.getPlayer(args[0])) == null) {
                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
                    return true;
                }

                subjectPlayer.setHealth(20);

                if (subjectPlayer.getName().endsWith("s")) {

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + subjectPlayer.getName() + "' health has been filled successfully."));

                } else {

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + subjectPlayer.getName() + "'s health has been filled successfully."));

                }

                subjectPlayer.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour health has been filled successfully."));

            } else {

                p.setHealth(20);
                p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour health has been filled successfully."));

            }

        }

        return true;

    }

}