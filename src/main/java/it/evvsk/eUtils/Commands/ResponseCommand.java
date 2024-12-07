package it.evvsk.eUtils.Commands;

import it.evvsk.eUtils.Core;
import it.evvsk.eUtils.Utils.SC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class ResponseCommand implements CommandExecutor {

    Core core = Core.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cYou must be a player to perform this command."));
            return true;
        }

        if (!p.hasPermission("eutils.command.response")) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cInsufficient permissions."));
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cCorrect usage: /r <message>"));
            return true;
        }

        final Player target = core.getLastMessagedPlayerList().get(p);

        if (target == null) {
            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cYou don't have anyone to respond to"));
            return true;
        }
        final String message = String.join(" ", args);
        target.sendMessage(SC.CC("From &e&l" + p.getName() + " &8» &f" + message));
        p.sendMessage(SC.CC("To &e&l" + target.getName() + " &8» &f" + message));
        return true;
    }
}
