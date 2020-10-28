package net.aksyo.game.tasks;

import net.aksyo.AcesUHC;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import net.aksyo.utils.BasicUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import sun.applet.Main;

public class StartGameTask extends BukkitRunnable {

    private int minimumPlayers;
    private int startTime;
    private int index;
    private GameMode option;

    private AcesUHC acesUHC = AcesUHC.getInstance();
    private GameManager gManager = acesUHC.getGameManager();

    private String prefix = AcesUHC.prefix;

    public StartGameTask(int minimumPlayers, int startTime, GameMode option) {
        this.minimumPlayers = minimumPlayers;
        this.startTime = startTime;
        this.index = startTime;
        this.option = option;
    }

    @Override
    public void run() {

        if (gManager.isGameState(GameState.FROZEN)) return;

        if (startTime > 10) {

            if (startTime % 10 == 0) {
                BasicUtils.silentBroadcast(prefix + "§6La game commence dans §e" + index + "§ secondes");
            }

        }

        if (startTime <= 10) {

            if (BasicUtils.getGameStartingPlayers(option).size() < minimumPlayers) {

                BasicUtils.silentBroadcast(prefix + "§cPas assez de joueurs pour demarrer la game! Remise a zero du compteur");
                index = startTime;
            }

            if(startTime == 1) {

                acesUHC.getWorldManager().teleportPlayers();
                acesUHC.getWorldManager().initializeMap(new Location(acesUHC.getInstance().getServer().getWorlds().get(0), 0, 0, 0), 1000);
                acesUHC.getWorldManager().createWorldBorder(acesUHC.getInstance().getServer().getWorlds().get(0));
                gManager.setMovement(false);
                acesUHC.getTeamManager().distribute(BasicUtils.getGameStartingPlayers(option));

                getReleaseTask().runTaskTimer(acesUHC, 20 ,20);

                cancel();
            }

        }

        startTime--;

    }

    private BukkitRunnable getReleaseTask() {

        return new BukkitRunnable() {

            int index = 10;

            @Override
            public void run() {

                if(index == 0) {

                    gManager.setMovement(true);
                    acesUHC.getWorldManager().removeCages();

                    new MainGameTask(30, 15, 60, 20, 0, 20, 1).runTaskTimer(acesUHC, 20, 0);

                    cancel();
                }

                if(index <= 3 && index != 0) {
                    BasicUtils.silentBroadcast("§6" + index);
                }

                index--;

            }
        };
    }
}
