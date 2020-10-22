package net.aksyo.game.roles.gamesroles.subroles;

import net.aksyo.AcesUHC;
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
        return null;
    }

    @Override
    public void revealAction(AcePlayer player) {

    }

}
