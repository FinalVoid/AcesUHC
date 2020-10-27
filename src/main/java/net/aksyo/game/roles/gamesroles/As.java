package net.aksyo.game.roles.gamesroles;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.GameRole;

public class As extends GameRole {

    @Override
    public String getName() {
        return "As";
    }

    @Override
    public String getGameName() {
        return AcesUHC.getInstance().getGameModel().roles.get("as").replace("&", "§");
    }

    @Override
    public String getDescription() {
        return "Vous etes l'As de l'equipe! Votre de but est de retrouver vos pouvoirs qui sont\ncachés dans un coffre.";
    }

    @Override
    public String[] getInformation() {
        return new String[] {
                "§bVotre objectif est survivre, et de retrouver vos pouvoirs qui sont enfouis dans un coffre caché.",
                "§e/a pouvoir §6- §6Cela te permet de voir les coordonnees brouillés du coffre"
        };
    }

    @Override
    public boolean isJoker() {
        return false;
    }

}
