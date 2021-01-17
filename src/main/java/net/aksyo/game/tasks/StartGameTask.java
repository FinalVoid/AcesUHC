package net.aksyo.game.tasks;

import net.aksyo.AcesUHC;
import net.aksyo.game.GameOption;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.roles.ITeam;
import net.aksyo.game.teams.JokerTeam;
import net.aksyo.utils.BasicUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import sun.applet.Main;

public class StartGameTask extends BukkitRunnable {

    private int minimumPlayers;
    private int startTime;
    private int index;
    private GameMode option, newOption;
    private final GameOption gameOption;

    private AcesUHC acesUHC = AcesUHC.getInstance();
    private GameManager gManager = acesUHC.getGameManager();

    private String prefix = AcesUHC.prefix;

    public StartGameTask(int minimumPlayers, int startTime, GameMode option, GameOption gameOption) {
        this.minimumPlayers = minimumPlayers;
        this.startTime = startTime;
        this.index = startTime;
        this.option = option;
        this.gameOption = gameOption;
    }

    @Override
    public void run() {

        if (gManager.isGameState(GameState.FROZEN)) return;

        if (index >= 10) {

            if (index % 10 == 0) {
                BasicUtils.silentBroadcast(prefix + "§6La game commence dans §e" + index + " §6secondes");
            }

        }

        if (index <= 10) {

            if (BasicUtils.getGameStartingPlayers(option).size() < minimumPlayers) {

                BasicUtils.silentBroadcast(prefix + "§cPas assez de joueurs pour demarrer la game! Remise a zero du compteur");
                index = startTime;
            }

            if(index == 1) {

                acesUHC.getWorldManager().initializeMap(new Location(acesUHC.getInstance().getWorldManager().world, 0, 120, 0), 800); //TODO Put back 0 0 0
                BasicUtils.getGameStartingPlayers(option).forEach(p -> p.setGameMode(GameMode.SURVIVAL));
                acesUHC.getTeamManager().distribute(BasicUtils.getGameStartingPlayers(GameMode.SURVIVAL));

                //We want to make the player spawn in solo if the Game Option hasn't be set
                //The solo spawn is the default Game Option
                if (gameOption == GameOption.TEAMSPAWN) {
                    acesUHC.getWorldManager().teleportTeams();
                } else {
                    acesUHC.getWorldManager().teleportPlayers();
                }

                acesUHC.getWorldManager().createWorldBorder(acesUHC.getInstance().getServer().getWorlds().get(0));
                gManager.setMovement(false);

                this.newOption = GameMode.SURVIVAL;

                getReleaseTask().runTaskTimer(acesUHC, 20 ,20);

                cancel();
            }

        }

        System.out.println("Start game task value : " + index);
        index--;

    }

    private BukkitRunnable getReleaseTask() {

        return new BukkitRunnable() {

            int index = 10;

            @Override
            public void run() {

                if(index == 0) {

                    gManager.setMovement(true);

                    acesUHC.getWorldManager().removeCages(gameOption);
                    acesUHC.getTeamManager().setStartingPlayers(newOption);

                    new MainGameTask(30, 15, 60, 20, 0.5, 20, 1, 75).runTaskTimer(acesUHC, 0, 20);

                    AcesUHC.getInstance().getTeamManager().spawnChests();

                    gManager.setGameState(GameState.GAME);

                    cancel();
                }

                if(index <= 3 && index != 0) {
                    BasicUtils.silentBroadcast("§6Liberation dans : " + index);
                }

                index--;

            }
        };
    }

}
