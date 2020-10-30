package net.aksyo.command;

import net.aksyo.AcesUHC;
import net.aksyo.player.AcePlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BugCommand extends AceCommand{


    public BugCommand() {
        super("bug", "Permet a un joueur de report un bug", false);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        AcePlayer acePlayer = AcesUHC.getInstance().getTeamManager().getAcePlayer(player);

        if (acePlayer != null) {

            Bukkit.broadcastMessage(AcesUHC.adminPrefix + "§a" + player.getName() + "§c aimerait report un bug!");
            player.sendMessage("§aVotre report de bug a été signalé a un ADMIN. §6§lVeuillez join le Channel Bug-Report sur le Discord");

        }

    }
}
