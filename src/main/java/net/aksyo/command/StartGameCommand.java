package net.aksyo.command;

import net.aksyo.AcesUHC;
import net.aksyo.game.GameOption;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.tasks.StartGameTask;
import net.aksyo.utils.GUI;
import net.aksyo.utils.ItemBuilder;
import net.aksyo.utils.LogFormat;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicReference;

public class StartGameCommand extends AceCommand {

    protected GUI gameOptionGUI;

    private AcesUHC acesUHC = AcesUHC.getInstance();
    private GameManager gManager = acesUHC.getGameManager();
    private AtomicReference<GameOption> gameOptionReference = new AtomicReference<>();

    //TODO finish the game start

    public StartGameCommand() {
        super("start", "Permet de start une game", true);
        setGameOptionGUI();
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (gManager.isGameState(GameState.WAITING)) {

            gameOptionGUI.open(player);

        } else {
            player.sendMessage(AcesUHC.adminPrefix + "§cGame launch failed");

            switch (gManager.getCurrentGameState()) {

                case GAME:
                    acesUHC.log(LogFormat.ERROR, " Lancement de game failed. Une game est deja en cours!");
                    break;
                case ENDING:
                    acesUHC.log(LogFormat.ERROR, " Lancement de game failed. La game se termine bientot!");
                    break;
                case CLOSED:
                    acesUHC.log(LogFormat.INFO, "Veuillez redemarrer le server afin de relancer une game! [CHANGE SOON]");
                    break;

                default:
                    acesUHC.log(LogFormat.ERROR, "Lancement de game failed");
                    break;

            }

        }

    }

    private void setGameOptionGUI() {

        gameOptionGUI = new GUI(acesUHC, "§aGame Option", 1);
        gameOptionGUI.setItem(2, new ItemBuilder(Material.WOOD_DOOR).setName("§6Solo Spawn").lore(
                " ",
                "§9Lance une game, en faisant",
                "§9spawn les joueurs tout seul"
        ).create(), ((player, inventoryClickEvent) -> {

            gameOptionReference.set(GameOption.SOLOSPAWN);

        }));
        gameOptionGUI.setItem(6, new ItemBuilder(Material.IRON_DOOR).setName("§6Team Spawn").lore(
                " ",
                "§9Lance une game, en faisant",
                "§9spawn les joueurs par equipe"
        ).create(), ((player, inventoryClickEvent) -> {

            gameOptionReference.set(GameOption.TEAMSPAWN);

        }));

        return;

    }

}
