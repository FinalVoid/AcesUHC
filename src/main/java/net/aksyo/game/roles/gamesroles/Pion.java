package net.aksyo.game.roles.gamesroles;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.Team;

public class Pion extends GameRole {

    @Override
    public String getName() {
        return "Pion";
    }

    @Override
    public String getGameName() {
        return "§5Pion";
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
        return false;
    }

}
