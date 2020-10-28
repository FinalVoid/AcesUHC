package net.aksyo.game.tasks;

import net.aksyo.AcesUHC;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import org.bukkit.scheduler.BukkitRunnable;

public class MainGameTask extends BukkitRunnable{

    //Constructor variables
    private int timeToStartWhispers;
    private int timeBetweenWhispers;
    private int timeToStopWhispers;
    private int jokerPacteAvailable;
    private int roleReveal;
    private int pvp;
    private int immune;

    //Class variables
    private int index = 0;
    private int totalWhispers = 1;

    private AcesUHC acesUHC = AcesUHC.getInstance();
    private GameManager gManager = acesUHC.getGameManager();

    private String prefix = AcesUHC.prefix;

    public MainGameTask(int timeToStartWhispers, int timeBetweenWhispers, int timeToStopWhispers, int jokerPacteAvailable, int roleReveal, int pvp, int immune) {
        this.timeToStartWhispers = timeToStartWhispers * 60;
        this.timeBetweenWhispers = timeBetweenWhispers * 60;
        this.timeToStopWhispers = timeToStopWhispers * 60;
        this.jokerPacteAvailable = jokerPacteAvailable * 60;
        this.roleReveal = roleReveal * 60;
        this.pvp = pvp * 60;
        this.immune = immune * 60;
    }

    @Override
    public void run() {

        if (gManager.isGameState(GameState.FROZEN)) return;

        if (index == jokerPacteAvailable) {
            gManager.setJokerPacte(true);
        }

        if (index >= timeToStartWhispers && index <= timeToStopWhispers) {

            if (index == timeToStartWhispers || index == timeToStartWhispers + (timeBetweenWhispers * totalWhispers) || index == timeToStopWhispers) {
                //Whisper
                totalWhispers++;
            }

        }

        if (index == roleReveal) {
            acesUHC.getTeamManager().revealRoles();
        }

        if (index == pvp) {
            gManager.setPvp(true);
        }

        if (index == immune) {
            gManager.setDamage(true);
        }



        index++;

    }
}
