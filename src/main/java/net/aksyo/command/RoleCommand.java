package net.aksyo.command;

import net.aksyo.AcesUHC;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;

public class RoleCommand extends AceCommand{


    public RoleCommand() {
        super("role", "Permet de voir son role", false);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (AcesUHC.getInstance().getTeamManager().getAcePlayer(player) != null) {

            AcePlayer acePlayer = AcesUHC.getInstance().getTeamManager().getAcePlayer(player);

            player.sendMessage("§3Role : §l" + acePlayer.getRoleType().get().getGameName());
            for (String str : acePlayer.getRoleType().get().getInformation()) {
                player.sendMessage(str);
            }

        } else {
            player.sendMessage("§cVous n'avez pas de role");
        }

    }
}
