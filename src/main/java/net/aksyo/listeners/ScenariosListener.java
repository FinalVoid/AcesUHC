package net.aksyo.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ScenariosListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Block block = event.getBlock();
        Material material = block.getType();



        switch (material) {

            case GOLD_ORE:
                event.setCancelled(true);
                block.setType(Material.AIR);
                block.getState().update();
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
                ExperienceOrb exp = (ExperienceOrb) block.getWorld().spawn(event.getBlock().getLocation().add(0, 1, 0), ExperienceOrb.class);
                exp.setExperience(3);
                break;

            case IRON_ORE:
                block.setType(Material.AIR);
                block.getState().update();
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack (Material.IRON_INGOT));
                ExperienceOrb exp1 = (ExperienceOrb) block.getWorld().spawn(event.getBlock().getLocation().add(0, 1, 0), ExperienceOrb.class);
                exp1.setExperience(3);
                break;

            case SAND:
                block.setType(Material.AIR);
                block.getState().update();
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack (Material.GLASS));
                break;

        }

    }

}
