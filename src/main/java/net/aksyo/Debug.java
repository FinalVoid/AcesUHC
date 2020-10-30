package net.aksyo;

import net.aksyo.game.roles.ITeam;

import java.util.Random;

public class Debug {

    public static void main(String[] args) {
        //distribute(17);
        String test = "Wesh les reys";
        System.out.println(test.toCharArray()[0]);
    }

    public static void distribute(final int players) {

        if ((players - 1) % 4 == 0) {

            System.out.println("Set Joker - Solo Role");
            final int teamPlayers = players - 1;

           /* for (ITeam ITeam : AcesUHC.getInstance().getTeamManager().getPrincipalTeams()) {
                boolean isJokerSet = false, isPionFourbeSet = false;
                for (int i = 0; i < teamPlayers % 4; i++) {



                }
            } */


        } else {
            System.out.println("Cannot distribute roles");
        }

    }
}
