package net.aksyo.command;

import net.aksyo.AcesUHC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SetGameModeCommand extends AceCommand {

    private String admin = AcesUHC.adminPrefix;

    public SetGameModeCommand() {
        super("gm", "Set tout les joueurs dans un gamemode", true);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (args.length == 2) {

            String gamemode = args[1].toUpperCase();
            if (GameMode.valueOf(gamemode) != null) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setGameMode(GameMode.valueOf(gamemode));
                    player.sendMessage(admin + "§aSet le gamemode en §b" + gamemode);
                }
            } else {
                player.sendMessage(admin + "§cLe gamemode n'existe pas");
            }

        } else {
            player.sendMessage(admin + "§c/a gm §e<mode>");
        }

    }
}
