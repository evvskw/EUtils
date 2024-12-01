package it.evvsk.eUtils.Events;

import it.evvsk.eUtils.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class GodEvent implements Listener {

    @EventHandler
    public void onEntityDamageByEntityEvent(final EntityDamageByEntityEvent e) {

        if (Core.getInstance().getGodCommand().godPlayers.contains(e.getEntity())) {

            e.setCancelled(true);

        }

    }

}
