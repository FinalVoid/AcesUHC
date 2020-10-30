package net.aksyo.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodListener implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {


        if (event.getEntity() instanceof Player) {
            //Player player = event.getEntity();
        }


    }
}
