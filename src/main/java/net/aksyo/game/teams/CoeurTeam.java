package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;

public class CoeurTeam implements Team {

    private static CoeurTeam instance = new CoeurTeam();

    public static CoeurTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Coeur";
    }

    @Override
    public String getGameName() {
        return "§cCoeur";
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
