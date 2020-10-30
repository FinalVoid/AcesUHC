package net.aksyo.game.managers;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.ITeam;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

public class ChestManager {

    private int diameter;

    public ChestManager(int radius) {
        this.diameter = radius * 2;
    }

    public Chest spawnChest(ITeam team) {

        Location chestLocation = generateRandomLocation(team);

        while (chestLocation.clone().subtract(0, 1, 0).getBlock().getType() == Material.AIR) {
            int y = new Random().nextInt(150);
            System.out.println("Repeated Y : " + y + " Team : " + team.getName());
            chestLocation = new Location(AcesUHC.getInstance().getWorldManager().world, chestLocation.getX(), y, chestLocation.getZ());
        }

        int slot = new Random().nextInt(27);
        Block block = chestLocation.getBlock();
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();
        chest.getBlockInventory().setItem(slot, generatePowerItem(team));

        return chest;

    }

    private Location generateRandomLocation(ITeam team) {
        int x = - (diameter) + new Random().nextInt(diameter * 2);
        int y = new Random().nextInt(150);
        int z = -diameter + new Random().nextInt(diameter * 2);
        System.out.println("Location " + team.getName() + " X : " + x + " Y : " + y + " Z : " + z);

        return new Location(AcesUHC.getInstance().getWorldManager().world, x, y, z);
    }

    private ItemStack generatePowerItem(ITeam team) {

        ItemStack item = new ItemStack(Material.BANNER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(team.getName() + "§9 - Pouvoir de l'As");
        meta.setLore(Arrays.asList(" ", "§7Clicker sur l'item pour activer votre pouvoir"));
        item.setItemMeta(meta);

        return item;

    }

}
