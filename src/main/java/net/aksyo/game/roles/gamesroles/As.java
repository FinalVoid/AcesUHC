package net.aksyo.game.roles.gamesroles;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.Team;

public class As extends GameRole {

    @Override
    public String getName() {
        return "As";
    }

    @Override
    public String getGameName() {
        return "§3As";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isAs() {
        return true;
    }

    @Override
    public boolean isJoker() {
        return false;
    }

}
