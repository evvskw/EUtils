package it.evvsk.eUtils.Utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public final class SC {

    public static String CC(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
