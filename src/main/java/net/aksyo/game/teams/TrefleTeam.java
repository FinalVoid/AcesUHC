package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;

public class TrefleTeam implements Team {

    private static TrefleTeam instance = new TrefleTeam();

    public static TrefleTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Trefle";
    }

    @Override
    public String getGameName() {
        return "§Trefle";
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
