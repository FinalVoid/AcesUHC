package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;

public class Pion extends GameRole {

    @Override
    public String getName() {
        return "Pion";
    }

    @Override
    public String getGameName() {
        return AcesUHC.getInstance().getGameModel().roles.get("as").replace("&", "§");
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isJoker() {
        return false;
    }

}
