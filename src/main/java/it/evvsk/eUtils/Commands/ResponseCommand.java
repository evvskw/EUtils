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
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player p)) {
            Bukkit.getLogger().info("You can't run this command from the console.");
            return true;
        }

        if(args.length >= 1 && p.hasPermission("eutils.command.response")) {

            final Player lastSubjectPlayer;

            if((lastSubjectPlayer = core.getMsgCommand().lastMessagedPlayerList.get(p)) == null) {
                p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cYou don't have anyone to respond to"));
                return true;
            }

            final String message = String.join(" ", args);

            lastSubjectPlayer.sendMessage(SC.CC("From &e&l" + p.getName() + " &8» &f" + message));

            p.sendMessage(SC.CC("To &e&l" + lastSubjectPlayer.getName() + " &8» &f" + message));

        } else {

            p.sendMessage(SC.CC("&e&lE&f&lUtils &8» &cCorrect usage: /r <message>"));

        }

        return true;
    }
}
