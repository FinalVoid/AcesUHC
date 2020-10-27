package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;

public class PiquesTeam implements ITeam {

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
        return "§7Piques";
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
