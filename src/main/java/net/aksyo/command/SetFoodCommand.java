package net.aksyo.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetFoodCommand extends AceCommand {


    public SetFoodCommand() {
        super("food", "Food give", true);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        for (Player player1 : Bukkit.getOnlinePlayers()) {

            player1.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 20));

        }

        player.sendMessage("§aDone");
    }
}
