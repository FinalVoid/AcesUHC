package net.aksyo.listeners;

import net.aksyo.AcesUHC;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (!player.isOp()) {

            if (AcesUHC.getInstance().getGameManager().isMovement()) {
                player.teleport(event.getFrom());
            }

        }

    }

}
