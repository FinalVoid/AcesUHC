package net.aksyo.listeners;

import net.aksyo.AcesUHC;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.roles.RoleType;
import net.aksyo.player.AcePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    private TeamManager tManager = AcesUHC.getInstance().getTeamManager();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

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

        if (item != null) {
            if (item.getType() == Material.BANNER && item.hasItemMeta()) {
                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {

                    AcePlayer acePlayer = tManager.getAcePlayer(player);
                    if (acePlayer != null) {
                        if (acePlayer.getRoleType() == RoleType.AS) {
                            if (item.getItemMeta().getDisplayName().split("§")[0].equalsIgnoreCase(acePlayer.getTeam().getName())) {
                                acePlayer.getRoleType().get().applyPowers().accept(acePlayer);
                                acePlayer.getPlayer().getInventory().removeItem(item);
                                tManager.changeJokerTeam(acePlayer.getTeam());
                            }
                        }
                    }

                }
            }
        }

    }

}
