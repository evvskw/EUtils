package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public final class GMSpectatorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

        if (!(sender instanceof Player p)) {
            getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.gamemode.spectator")) {

            p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &cInsufficient permissions."));

        } else {

            if (args.length == 1) {

                final Player subjectPlayer;

                if ((subjectPlayer = Bukkit.getPlayer(args[0])) == null) {
                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &cPlayer not found."));
                    return true;
                }

                subjectPlayer.setGameMode(GameMode.SPECTATOR);

                if (subjectPlayer.getName().endsWith("s")) {

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &a" + subjectPlayer.getName() + "' gamemode has been changed successfully to spectator."));

                } else {

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &a" + subjectPlayer.getName() + "'s gamemode has been changed successfully to spectator."));

                }

                subjectPlayer.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &aYour gamemode has been changed successfully to spectator."));

            } else {

                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(SC.CC("&e&lE&f&lUtils &8&l» &aYour gamemode has been changed successfully to spectator."));

            }

        }

        return true;

    }

}