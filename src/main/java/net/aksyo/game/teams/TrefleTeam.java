package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;

public class TrefleTeam implements ITeam {

    private static TrefleTeam instance = new TrefleTeam();

    public static TrefleTeam getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "trefles";
    }

    @Override
    public String getGameName() {
        return "§7Trefles";
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
