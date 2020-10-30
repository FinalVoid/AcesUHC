package net.aksyo.listeners;

import net.aksyo.AcesUHC;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.player.AcePlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private TeamManager tManager = AcesUHC.getInstance().getTeamManager();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();

        /*if (action == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType() == Material.CHEST) {
                AcePlayer acePlayer = tManager.getAcePlayer(player);
                if (acePlayer != null) {
                    if (!tManager.checkChest(acePlayer.getTeam(), block)) {
                        event.setCancelled(true);
                    }

                }

            }
        } */

    }

}
