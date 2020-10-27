package net.aksyo.game.teams;

import net.aksyo.game.roles.GameRole;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;

public class CarreauxTeam implements ITeam {

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

}
