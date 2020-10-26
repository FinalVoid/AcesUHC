package net.aksyo.game.teams;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.SubRole;
import net.aksyo.game.roles.Team;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;

import java.util.HashMap;
import java.util.Map;

public class TrefleTeam implements Team {

    private static TrefleTeam instance = new TrefleTeam();

    public static TrefleTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "trefle";
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
        return new GameRole[] {
            RoleType.AS.get(),
            RoleType.PION.get()
        };
    }

}
