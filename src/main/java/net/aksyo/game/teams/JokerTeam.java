package net.aksyo.game.teams;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.SubRole;
import net.aksyo.game.roles.Team;

import java.util.Map;

public class JokerTeam implements Team {

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
        return AcesUHC.getInstance().getGameModel().teamModelList.get(getName());
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
