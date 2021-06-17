package net.aksyo.listeners;

import net.aksyo.AcesUHC;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.player.AcePlayer;
import net.aksyo.player.PlayerOption;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class AsyncChatListener implements Listener {

    protected TeamManager tManager = AcesUHC.getInstance().getTeamManager();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        AcePlayer acePlayer = tManager.getAcePlayer(event.getPlayer());

        if (acePlayer != null) {

            if (acePlayer.hasOption(PlayerOption.PLAYER)) {

                if (tManager.getTeamChatPlayers().contains(acePlayer)) {
                    tManager.getTeamMembers(acePlayer.getTeam()).forEach(p -> p.getPlayer().sendMessage("§4[TEAM] §9" + acePlayer.getPlayer().getName() + " §f: " + event.getMessage()));
                    event.setCancelled(true);
                    return;
                } else {
                    event.setFormat(AcesUHC.getInstance().getTabManager().getTeamTabDesign().get(acePlayer.getTeam()) + " §9" + acePlayer.getPlayer().getName() + " §f: " + event.getMessage());
                    return;
                }

            }

        }

    }

}
