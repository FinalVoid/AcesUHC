package net.aksyo.game.teams;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.SubRole;
import net.aksyo.game.roles.Team;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;

import java.util.HashMap;
import java.util.Map;

public class CarreauxTeam implements Team {

    private static CarreauxTeam instance = new CarreauxTeam();
    public static CarreauxTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "carreaux";
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
