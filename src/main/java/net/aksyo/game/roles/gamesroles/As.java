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
                "§9Votre objectif est de retrouver vos pouvoirs et gagner avec votre équipe.",
                "§9Toutes les 15 minutes, à partir de 30 minutes de jeu, un whisper vous révélera au fur et à mesure les coordonnées du coffre contenant vos pouvoirs.",
                "§9Si vous retrouvez vos pouvoirs, vous disposerez de l’effet Résistance 1, Vitesse 1 et Résistance au feu permanent."
        };
    }

    @Override
    public Consumer<AcePlayer> applyPowers() {
        return acePlayer -> {
            acePlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 24000, 0));
            acePlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 24000, 0));
            acePlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 24000, 0));
            acePlayer.getPlayer().sendMessage(AcesUHC.prefix + "§aTes pouvoirs d'As se sont activés. Tu as possède desormais un livre §cFire Resistance 1, Speed 1 et Resistance 1");
        };
    }

    @Override
    public boolean isJoker() {
        return false;
    }

}
