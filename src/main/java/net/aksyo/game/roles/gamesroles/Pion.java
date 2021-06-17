package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.player.AcePlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
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
        return "Aidez votre As a retrouver ses pouvoirs.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "§9Votre objectif est de gagner avec votre équipe et aider votre As à retrouver ses pouvoirs. ",
                "§9Tant que votre As sera en vie, vous aurez la possibilité de revivre à l’infini dans un rayon de 150 blocs autour du centre.",
                "§9Cependant, lors de la première réapparition, vous n’aurez plus que 8 cœurs puis 5 cœurs permanents pour les réapparitions suivantes."
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
                    ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
                    EnchantmentStorageMeta esm = (EnchantmentStorageMeta) item.getItemMeta();
                    esm.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
                    item.setItemMeta(esm);
                    player.getInventory().addItem(item);
                    player.sendMessage(prefix + "§aTes pouvoirs de Pions se sont activés. Tu as possède desormais un livre §cDepth Strider 3");
                    break;
                case 2:
                    ItemStack item2 = new ItemStack(Material.ENCHANTED_BOOK);
                    EnchantmentStorageMeta esm2 = (EnchantmentStorageMeta) item2.getItemMeta();
                    esm2.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
                    item2.setItemMeta(esm2);
                    player.getInventory().addItem(item2);
                    player.sendMessage(prefix + "§aTes pouvoirs de Pions se sont activés. Tu as possède desormais un livre §cFeather Falling 4");
                    break;
                case 3:
                    player.setFoodLevel(100);
                    AcesUHC.getInstance().getTeamManager().getSaturationPowerPlayers().add(acePlayer);
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
