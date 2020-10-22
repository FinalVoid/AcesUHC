package net.aksyo.game.teams;

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

    private Map<SubRole, Integer> subRoleMap = new HashMap<>();

    {
        subRoleMap.put(SubRoleType.PIONFOURBE.get(), 1);
    }

    @Override
    public String getName() {
        return "Carreaux";
    }

    @Override
    public String getGameName() {
        return "§cCarreaux";
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

    @Override
    public Map<SubRole, Integer> getSubRoles() {
        return subRoleMap;
    }
}
