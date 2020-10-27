package net.aksyo.listeners;

import net.aksyo.AcesUHC;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();
            AcePlayer acePlayer = AcesUHC.getInstance().getTeamManager().getAcePlayer(player);

            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (AcesUHC.getInstance().getGameManager().getImmunePlayers().contains(player)) {
                    event.setCancelled(true);
                    AcesUHC.getInstance().getGameManager().getImmunePlayers().remove(player);
                }
            }
        }

    }
}
