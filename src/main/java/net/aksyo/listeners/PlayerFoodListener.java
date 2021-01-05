package net.aksyo.listeners;

import net.aksyo.AcesUHC;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodListener implements Listener {

    protected TeamManager tManager = AcesUHC.getInstance().getTeamManager();

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {


        if (event.getEntity() instanceof Player) {
            AcePlayer acePlayer = tManager.getAcePlayer((Player) event.getEntity());
            if (acePlayer != null) {
                if (tManager.getSaturationPowerPlayers().contains(acePlayer)) {
                    acePlayer.getPlayer().setFoodLevel(100);
                    event.setCancelled(true);
                }
            }
        }


    }
}
