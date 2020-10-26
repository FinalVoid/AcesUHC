package net.aksyo.game.teams;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.SubRole;
import net.aksyo.game.roles.Team;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;

import java.util.HashMap;
import java.util.Map;

public class PiquesTeam implements Team {

    private static PiquesTeam instance = new PiquesTeam();

    public static PiquesTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "piques";
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
