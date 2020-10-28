package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.player.AcePlayer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        return "Vous etes seul contre tous. Mais un certain pacte permettera de rompre la solitude.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "Votre but est de gagner seul. Cependant vous recevez les requetes des Pion Fourbe qui veulent passer un pacte avec vous.",
                "§cPas encore determiner"
        };
    }

    @Override
    public Consumer<AcePlayer> applyPowers() {
        return acePlayer -> {
            ItemStack item = new ItemStack(Material.BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
            meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            item.setItemMeta(meta);
            acePlayer.getPlayer().getInventory().addItem(item);
            acePlayer.getPlayer().sendMessage(AcesUHC.prefix + "§aTes pouvoirs de Joker se sont activés. Tu as possède desormais un livre §cPower 2 Punch 1");
        };
    }

    @Override
    public boolean isJoker() {
        return true;
    }


}
