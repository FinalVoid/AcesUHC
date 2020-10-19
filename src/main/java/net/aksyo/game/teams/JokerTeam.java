package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;

public class JokerTeam implements Team {

    private static JokerTeam instance = new JokerTeam();

    public static JokerTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Joker";
    }

    @Override
    public String getGameName() {
        return "§2Joker";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public GameRole[] getRoles() {
        return new GameRole[] { RoleType.JOKER.get() } ;
    }
}
