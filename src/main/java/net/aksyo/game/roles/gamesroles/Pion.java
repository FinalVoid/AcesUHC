package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;

public class Pion extends GameRole {

    @Override
    public String getName() {
        return "Pion";
    }

    @Override
    public String getGameName() {
        return AcesUHC.getInstance().getGameModel().roles.get("as").replace("&", "§");
    }

    @Override
    public String getDescription() {
        return "Aidez votre As a retrouver ses pouvoirs, et demasquer le pion fourbe.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "§aVotre objectif est de gagner avec otre equipe et proteger l'As ainsi que de l'aider dans la traque de des pouvoirs.",
                "N'oubliez pas que l'un d'entre vous est fourbe!"
        };
    }

    @Override
    public boolean isJoker() {
        return false;
    }

}
