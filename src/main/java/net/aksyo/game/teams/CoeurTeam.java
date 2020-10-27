package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;

public class CoeurTeam implements ITeam {

    private static CoeurTeam instance = new CoeurTeam();

    public static CoeurTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "coeur";
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
