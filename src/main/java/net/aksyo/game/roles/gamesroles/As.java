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

public class As extends GameRole {

    @Override
    public String getName() {
        return "As";
    }

    @Override
    public String getGameName() {
        return AcesUHC.getInstance().getGameModel().roles.get("as").replace("&", "§");
    }

    @Override
    public String getDescription() {
        return "Vous etes l'As de l'equipe! Votre de but est de retrouver vos pouvoirs qui sont\ncachés dans un coffre.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "§bVotre objectif est survivre, et de retrouver vos pouvoirs qui sont enfouis dans un coffre caché.",
                "§e/a pouvoir §6- §6Cela te permet de voir les coordonnees brouillés du coffre"
        };
    }

    @Override
    public Consumer<AcePlayer> applyPowers() {
        return acePlayer -> {
            acePlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 24000, 1));
            acePlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 24000, 1));
            acePlayer.getPlayer().sendMessage(AcesUHC.prefix + "§aTes pouvoirs de Joker se sont activés. Tu as possède desormais un livre §cFire Resistance 1 et Speed 1");
        };
    }

    @Override
    public boolean isJoker() {
        return false;
    }

}
