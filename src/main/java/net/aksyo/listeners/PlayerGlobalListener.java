package net.aksyo.listeners;

import net.aksyo.AcesUHC;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.player.AcePlayer;
import net.aksyo.utils.LogFormat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;


public class PlayerGlobalListener implements Listener {

    protected TeamManager teamManager = AcesUHC.getInstance().getTeamManager();
    protected GameManager gManager = AcesUHC.getInstance().getGameManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {


            Optional<AcePlayer> optional = teamManager.getAcePlayers().stream().filter(a -> a.getUUID().compareTo(event.getPlayer().getUniqueId()) == 0).findFirst();

        if (optional.isPresent()) {

            AcePlayer acePlayer = optional.get();
            acePlayer.updatePlayerCache();

            if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Updated cache data of : " + acePlayer.getPlayer().getName());

        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {


    }

}
