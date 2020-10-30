package net.aksyo.game.roles.gamesroles.subroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.SubRole;
import net.aksyo.player.AcePlayer;

public class PionFourbe extends SubRole {

    @Override
    public String getName() {
        return "Pion Fourbe";
    }

    @Override
    public String getGameName() {
        return AcesUHC.getInstance().getGameModel().roles.get("pionfourbe").replace("&", "§");
    }

    @Override
    public String getDescription() {
        return "Vous etes le Pion Fourbe. Vous pouvez faire un pacte avec le Joker, et lui revelez vos coordonnees, mais prudence!";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "Votre objectif est de gagner avec votre équipe et aider votre As à retrouver ses pouvoirs. ",
                "Pour cela, vous disposez d’un atout : celui de passer un pacte avec le Joker. Si vous décidez de passer le pacte, vous avez une chance de gagner 5 ♡ permanents ou la malchance de les perdre. Peut-être que vous n’obtiendrez rien du tout… Le choix vous appartient !\n",
                "§c/a pacte §e- Pour passer le pacte"
        };
    }

    @Override
    public RoleType getParentRole() {
        return RoleType.PION;
    }

    @Override
    public void revealAction(AcePlayer player) {

    }

}
