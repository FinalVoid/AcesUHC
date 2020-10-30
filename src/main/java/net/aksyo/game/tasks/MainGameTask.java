package net.aksyo.game.tasks;

import net.aksyo.AcesUHC;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import net.aksyo.utils.BasicUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MainGameTask extends BukkitRunnable{

    //Constructor variables
    private int timeToStartWhispers;
    private double timeBetweenWhispers;
    private int timeToStopWhispers;
    private int jokerPacteAvailable;
    private double roleReveal;
    private int pvp;
    private int immune;

    //Class variables
    public static int index = 0;
    private int totalWhispers = 1;
    private double whispers;

    private AcesUHC acesUHC = AcesUHC.getInstance();
    private GameManager gManager = acesUHC.getGameManager();

    private String prefix = AcesUHC.prefix;

    public MainGameTask(int timeToStartWhispers, double timeBetweenWhispers, int timeToStopWhispers, int jokerPacteAvailable, double roleReveal, int pvp, int immune) {
        this.timeToStartWhispers = timeToStartWhispers * 60;
        System.out.println("Time Start Whispers : " + this.timeToStartWhispers);
        this.timeBetweenWhispers = timeBetweenWhispers * 60;
        System.out.println("Time Between Whispers : " + this.timeBetweenWhispers);
        this.timeToStopWhispers = timeToStopWhispers * 60;
        System.out.println("Time Stop Whispers : " + this.timeToStopWhispers);
        this.jokerPacteAvailable = jokerPacteAvailable * 60;
        System.out.println("Time Joker Pacte : " + this.jokerPacteAvailable);
        this.roleReveal = roleReveal * 60;
        System.out.println("Time Role : " + this.roleReveal);
        this.pvp = pvp * 60;
        System.out.println("Time PVP : " + this.pvp);
        this.immune = immune * 60;
        System.out.println("Time Immune : " + this.immune);

        whispers = (timeToStopWhispers - timeToStartWhispers) / timeBetweenWhispers + 1;
    }

    @Override
    public void run() {

        if (gManager.isGameState(GameState.FROZEN)) return;

        if (gManager.isGameState(GameState.ENDING)) cancel();

        if (index == jokerPacteAvailable) {
            gManager.setJokerPacte(true);
        }

        if (index >= timeToStartWhispers && index <= timeToStopWhispers) {

            if (index == timeToStartWhispers || index == timeToStartWhispers + (timeBetweenWhispers * totalWhispers) || index == timeToStopWhispers) {
                System.out.println("Index : " + index);
                AcesUHC.getInstance().getTeamManager().whisper(totalWhispers);
                if (index != timeToStartWhispers) {
                    totalWhispers++;
                }

            }

        }

        if (index == 200) {
            BasicUtils.silentBroadcast(prefix + "§cNe te surestimes pas Askekoi, Emma Watson c'est §lma meuf");
        }

        if (index == roleReveal) {
            acesUHC.getTeamManager().revealRoles();
        }

        if (index == pvp) {
            BasicUtils.silentBroadcast(prefix + "§6Le pvp est activé! ");
            System.out.println("Index : " + index);
            gManager.setPvp(true);
            BasicUtils.getGameStartingPlayers(GameMode.SURVIVAL).forEach(p -> p.setHealth(20));
        }

        if (index == immune) {
            BasicUtils.silentBroadcast(prefix + "§6Vous n'etes plus invulnerable! ");
            System.out.println("Index : " + index);
            gManager.setDamage(true);
        }

        index++;

    }
}
