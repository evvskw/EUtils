package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public final class GMCreativeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {

        if (!(sender instanceof Player p)) {
            getLogger().info("You must be a player to perform this command.");
            return true;
        }

        if (!p.hasPermission("eutils.command.gamemode.creative")) {

            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));

        } else {

            if (args.length == 1) {

                final Player subjectPlayer;

                if ((subjectPlayer = Bukkit.getPlayer(args[0])) == null) {
                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cPlayer not found."));
                    return true;
                }

                subjectPlayer.setGameMode(GameMode.CREATIVE);

                if (subjectPlayer.getName().endsWith("s")) {

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + subjectPlayer.getName() + "' gamemode has been changed successfully to creative."));

                } else {

                    p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &a" + subjectPlayer.getName() + "'s gamemode has been changed successfully to creative."));

                }

                subjectPlayer.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour gamemode has been changed successfully to creative."));

            } else {

                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &aYour gamemode has been changed successfully to creative."));

            }

        }

        return true;

    }

}