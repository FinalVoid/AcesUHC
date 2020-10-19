package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;

public class PiquesTeam implements Team {

    private static PiquesTeam instance = new PiquesTeam();

    public static PiquesTeam getInstance() {
        return instance;
    }


    @Override
    public String getName() {
        return "Piques";
    }

    @Override
    public String getGameName() {
        return "§2Piques";
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
