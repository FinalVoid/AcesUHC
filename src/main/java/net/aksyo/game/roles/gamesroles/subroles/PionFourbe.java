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
                "Votre objectif est de gagner avec votre equipe. Vous pouvez passer un pacte avec le Joker, s'il accepte le pacte,",
                "Vos coordonnées lui seront données, allez-vous prendre le risque de gagner 5 ♡, les perdre définitivement... ou de ne rien avoir ?",
                "§cPas encore determiner"

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
