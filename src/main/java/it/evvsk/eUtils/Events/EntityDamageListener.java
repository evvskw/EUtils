package it.evvsk.eUtils.Events;

import it.evvsk.eUtils.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public final class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent e) {

        if (Core.getInstance().getGodCommand().godPlayers.contains(e.getEntity())) {

            e.setCancelled(true);

        }

    }

}