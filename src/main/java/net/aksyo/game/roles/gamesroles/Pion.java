package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.player.AcePlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;
import java.util.function.Consumer;

public class Pion extends GameRole {

    @Override
    public String getName() {
        return "Pion";
    }

    @Override
    public String getGameName() {
        return AcesUHC.getInstance().getGameModel().roles.get("pion").replace("&", "§");
    }

    @Override
    public String getDescription() {
        return "Aidez votre As a retrouver ses pouvoirs, et demasquer le pion fourbe.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "§aVotre objectif est de gagner avec otre equipe et proteger l'As ainsi que de l'aider dans la traque de des pouvoirs.",
                "N'oubliez pas que l'un d'entre vous est fourbe!"
        };
    }

    @Override
    public Consumer<AcePlayer> applyPowers() {
        return acePlayer -> {
            int power = new Random().nextInt(4);
            Player player = acePlayer.getPlayer();
            String prefix = AcesUHC.prefix;
            System.out.println("Power, Value : " + power);
            switch (power) {
                case 0:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 1));
                    player.sendMessage(prefix + "§aTes pouvoirs de Pions se sont activés. Tu as desormais §cFire Resistance");
                    break;
                case 1:
                    ItemStack item = new ItemStack(Material.BOOK);
                    ItemMeta meta = item.getItemMeta();
                    meta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
                    item.setItemMeta(meta);
                    player.getInventory().addItem(item);
                    player.sendMessage(prefix + "§aTes pouvoirs de Pions se sont activés. Tu as possède desormais un livre §cDepth Strider 3");
                    break;
                case 2:
                    ItemStack item2 = new ItemStack(Material.BOOK);
                    ItemMeta meta2 = item2.getItemMeta();
                    meta2.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
                    item2.setItemMeta(meta2);
                    player.getInventory().addItem(item2);
                    player.sendMessage(prefix + "§aTes pouvoirs de Pions se sont activés. Tu as possède desormais un livre §cFeather Falling 4");
                    break;
                case 3:
                    player.setFoodLevel(100);
                    player.sendMessage(prefix + "§aTes pouvoirs de Pions se sont activés. Tu as desormais \n§cSaturation Illimité");
                    break;

            }
        };
    }

    @Override
    public boolean isJoker() {
        return false;
    }

}
