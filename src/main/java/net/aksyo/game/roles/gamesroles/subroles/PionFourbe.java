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
        return "Vous etes le Pion Fourbe. Vous pouvez faire un pacte avec le Joker, et trahir votre equipe.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "Votre objectif n'est pas precis, si vous ne passez un pacte avec le Joker, vous devez gagner avec votre equipe.",
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
