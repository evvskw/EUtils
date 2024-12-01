package it.evvsk.eUtils.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import it.evvsk.eUtils.Utils.SC;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getLogger;

public final class GodCommand implements CommandExecutor {

    public final ArrayList<Player> godPlayers = new ArrayList<Player>();

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

        if (!(sender instanceof Player p)) {
            getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.god")) {

            p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &cInsufficient permissions."));

        } else {

            if (args.length == 1) {

                final Player subjectPlayer = Bukkit.getPlayer(args[0]);

                if (subjectPlayer == null) {
                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &cPlayer not found."));
                    return true;
                }

                if (godPlayers.contains(subjectPlayer)) {

                    godPlayers.remove(subjectPlayer);

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &c" + subjectPlayer.getName() + " has now godmode disabled."));
                    subjectPlayer.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &cYou have now godmode disabled."));

                } else {

                    godPlayers.add(subjectPlayer);

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &a" + subjectPlayer.getName() + " has now godmode enabled."));
                    subjectPlayer.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &aYou have now godmode enabled."));

                }

            } else {

                if (godPlayers.contains(p)) {

                    godPlayers.remove(p);
                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &cYou have now godmode disabled."));

                } else {

                    godPlayers.add(p);
                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &aYou have now godmode enabled."));

                }

            }

        }

        return true;
    }

}