package net.aksyo.command;

import net.aksyo.AcesUHC;
import net.aksyo.game.GameState;
import net.aksyo.utils.BasicUtils;
import org.bukkit.entity.Player;

public class FreezeGameCommand extends AceCommand {

    public FreezeGameCommand() {
        super("freeze", "Freezes the game", true);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (args.length == 1) {
            if(AcesUHC.getInstance().getGameManager().isGameState(GameState.FROZEN)) {
                AcesUHC.getInstance().getGameManager().unfreezeGame();
                BasicUtils.silentBroadcast(AcesUHC.adminPrefix + "§9" + player.getName() + " §cunfreezed §bthe game.");
                player.sendMessage(AcesUHC.adminPrefix + "§bGame is now §cunfreezed");
                return;
            }
            AcesUHC.getInstance().getGameManager().freezeGame(true);
            BasicUtils.silentBroadcast(AcesUHC.adminPrefix + "§9" + player.getName() + " §bfreezed the game");
            player.sendMessage(AcesUHC.adminPrefix + "§bGame is now freezed");
            return;
        }

        if (args[1].equalsIgnoreCase("stop")) {
            AcesUHC.getInstance().getServer().getPluginManager().disablePlugin(AcesUHC.getInstance());
            return;
        }


    }
}
