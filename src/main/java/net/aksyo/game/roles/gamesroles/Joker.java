package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.player.AcePlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.function.Consumer;

public class Joker extends GameRole {


    @Override
    public String getName() {
        return "Joker";
    }

    @Override
    public String getGameName() {
        return AcesUHC.getInstance().getGameModel().roles.get("joker").replace("&", "§");
    }

    @Override
    public String getDescription() {
        return "Vous etes le Joker, vous etes seul tant qu'aucun As a retrouvé ses pouvoir.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "§9Votre objectif est de gagner en solitaire tant que vous n’avez pas de camp. Pour cela, vous disposez de l’effet Résistance 1 ainsi que d’un livre power 2 punch 1.",
                "§9Dès que l’As d’une équipe retrouvera ses pouvoirs, vous serez intégré directement à cette dernière et perdrez votre effet de résistance.",
                "§9De plus, vous disposez d’un avantage : en passant des pactes avec les Pions fourbes, leurs coordonnées vous seront dévoilées. Faites-en bon usage…"
        };
    }

    @Override
    public Consumer<AcePlayer> applyPowers() {
        return acePlayer -> {
            acePlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 24000, 0));
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta esn = (EnchantmentStorageMeta) item.getItemMeta();
            esn.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
            esn.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            item.setItemMeta(esn);
            acePlayer.getPlayer().getInventory().addItem(item);
            acePlayer.getPlayer().sendMessage(AcesUHC.prefix + "§aTes pouvoirs de Joker se sont activés. Tu possède desormais un livre §cPower 2 Punch 1");
        };
    }

    @Override
    public boolean isJoker() {
        return true;
    }


}
