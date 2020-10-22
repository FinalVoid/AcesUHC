package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.Team;

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
        return null;
    }

    @Override
    public boolean isJoker() {
        return true;
    }


}
