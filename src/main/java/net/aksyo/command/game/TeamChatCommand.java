package net.aksyo.command.game;

import net.aksyo.AcesUHC;
import net.aksyo.command.AceCommand;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;

public class TeamChatCommand extends AceCommand {

    private TeamManager tManager = AcesUHC.getInstance().getTeamManager();

    public TeamChatCommand() {
        super("tc", "Team chat command", false);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (AcesUHC.getInstance().getTeamManager().getAcePlayer(player) != null) {

            AcePlayer acePlayer = AcesUHC.getInstance().getTeamManager().getAcePlayer(player);

            if (tManager.getTeamChatPlayers().contains(acePlayer)) {
                tManager.getTeamChatPlayers().remove(acePlayer);
                player.sendMessage("§aVous etes desormais dans le §4GLOBAL §achat");
                return;
            }

            tManager.getTeamChatPlayers().add(acePlayer);
            player.sendMessage("§aVous etes desormais dans le §4TEAM §achat");

        } else {
            player.sendMessage("§cVous ne pouvez pas faire cette commande");
        }

    }
}
