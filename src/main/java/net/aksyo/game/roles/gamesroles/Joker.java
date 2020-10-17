package net.aksyo.game.roles.gamesroles;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.Team;

public class Joker extends GameRole {


    @Override
    public String getName() {
        return "Joker";
    }

    @Override
    public String getGameName() {
        return "§6Joker";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isAs() {
        return false;
    }

    @Override
    public boolean isJoker() {
        return true;
    }


}
