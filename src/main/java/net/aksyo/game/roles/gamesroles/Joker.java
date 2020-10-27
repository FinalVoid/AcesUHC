package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;

public class Joker extends GameRole {


    @Override
    public String getName() {
        return "Joker";
    }

    @Override
    public String getGameName() {
        return AcesUHC.getInstance().getGameModel().roles.get("joker").replace("&", "§");
    }

    @Override
    public String getDescription() {
        return "Vous etes seul contre tous. Mais un certain pacte permettera de rompre la solitude.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "Votre but est de gagner seul. Cependant vous recevez les requetes des Pion Fourbe qui veulent passer un pacte avec vous.",
                "§cPas encore determiner"
        };
    }

    @Override
    public boolean isJoker() {
        return true;
    }


}
