package net.aksyo.command;

import net.aksyo.AcesUHC;
import net.aksyo.game.GameOption;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.tasks.StartGameTask;
import net.aksyo.utils.GUI;
import net.aksyo.utils.ItemBuilder;
import net.aksyo.utils.LogFormat;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicReference;

public class StartGameCommand extends AceCommand {

    protected GUI gameOptionGUI;
    protected GUI gameParametersGUI;

    private AcesUHC acesUHC = AcesUHC.getInstance();
    private GameManager gManager = acesUHC.getGameManager();
    private AtomicReference<GameOption> gameOptionReference = new AtomicReference<>();
    private AtomicReference<GameMode> gameModeReference = new AtomicReference<>();

    //TODO finish the game start

    public StartGameCommand() {
        super("start", "Permet de start une game", true);
        setGameOptionGUI();
        setGameParameters();
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (gManager.isGameState(GameState.WAITING)) {

            gameOptionGUI.open(player);

            //new StartGameTask(1, 11, player.getGameMode(), gameOptionReference.get()).runTaskTimer(AcesUHC.getInstance(), 0, 20);

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
            player.closeInventory();
            gameParametersGUI.open(player);
            playSelectSound(player);

        }));
        gameOptionGUI.setItem(6, new ItemBuilder(Material.IRON_DOOR).setName("§6Team Spawn").lore(
                " ",
                "§9Lance une game, en faisant",
                "§9spawn les joueurs par equipe"
        ).create(), ((player, inventoryClickEvent) -> {

            gameOptionReference.set(GameOption.TEAMSPAWN);
            player.closeInventory();
            gameParametersGUI.open(player);
            playSelectSound(player);


        }));

        gameOptionGUI.setLocked(true);

        return;

    }

    private void setGameParameters() {

        gameParametersGUI = new GUI(acesUHC, "§aGame Parameters", 4);
        if (!gManager.isDebug()) {
            activateDebug();
        } else {
            deactivateDebug();
        }

        if (gameModeReference.get() == null || gameModeReference.get() == GameMode.ADVENTURE) {
            setGameModeAdventure();
        } else {
            setGameModeSurvival();
        }

        gameParametersGUI.setItem(31, new ItemBuilder(Material.REDSTONE_LAMP_OFF).setName("§6Launch Game").create(), ((player, inventoryClickEvent) -> {
            new StartGameTask(1, 11, gameModeReference.get(), gameOptionReference.get()).runTaskTimer(AcesUHC.getInstance(), 0, 20);
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1f, 1f);
            player.sendMessage(AcesUHC.adminPrefix + "§bThe game has been launched!");
            player.closeInventory();
        }));

        gameParametersGUI.setLocked(true);

    }

    private void setGameModeAdventure() {
        gameParametersGUI.setItem(15, new ItemBuilder(Material.GOLD_CHESTPLATE).setName("§cSwitch to : §2ADVENTURE").lore(
                " ",
                "§9Current start mode : §bSURVIVAL",
                " ",
                "§9Cliquer pour changer le mode de lancement",
                "§9vers le mode : §2ADVENTURE"
        ).create(), (player, inventoryClickEvent) -> {
            gameModeReference.set(GameMode.ADVENTURE);
            setGameModeSurvival();
            playSelectSound(player);
        });
    }

    private void setGameModeSurvival() {
        gameParametersGUI.setItem(15, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§cSwitch to : §2SURVIVAL").lore(
                " ",
                "§9Current start mode : §bADVENTURE",
                " ",
                "§9Cliquer pour changer le mode de lancement",
                "§9vers le mode : §2SURVIVAL"
        ).create(), (player, inventoryClickEvent) -> {
            gameModeReference.set(GameMode.SURVIVAL);
            setGameModeAdventure();
            playSelectSound(player);
        });
    }

    private void activateDebug() {
        gameParametersGUI.setItem(11, new ItemBuilder(Material.DETECTOR_RAIL).setName("§cActivate Debug").lore(
                " ",
                "§bLe debug est : §cinactif",
                " ",
                "§9Active le mode debug",
                "§9pour la game."
        ).create(), (player, inventoryClickEvent) -> {
            gManager.activateDebug();
            deactivateDebug();
            playSelectSound(player);
        });
    }

    private void deactivateDebug() {
        gameParametersGUI.setItem(11, new ItemBuilder(Material.DETECTOR_RAIL).setName("§cDe-activate Debug").lore(
                " ",
                "§bLe debug est : §aactif",
                " ",
                "§9Desactive le mode debug",
                "§9pour la game."
        ).create(), (player, inventoryClickEvent) -> {
            gManager.deactivateDebug();
            activateDebug();
            playSelectSound(player);
        });
    }

    private void playSelectSound(Player player) {

        player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);

    }

}
