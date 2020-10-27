package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;

public class JokerTeam implements ITeam {

    private static JokerTeam instance = new JokerTeam();

    public static JokerTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "joker";
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
    public GameRole[] getRoles() {
        return new GameRole[] { RoleType.JOKER.get() } ;
    }

}
