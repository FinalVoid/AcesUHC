package net.aksyo.listeners;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.player.AcePlayer;
import net.aksyo.utils.BasicUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        AcePlayer acePlayer = AcesUHC.getInstance().getTeamManager().getAcePlayer(player);
        event.setDeathMessage(" ");

        if (acePlayer != null) {

            BasicUtils.silentBroadcast("§c" + player.getName() + "§e est mort! C'etais un : " + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName())
            + " §eIl faisait partie de l'equipe des : " + acePlayer.getTeam().getGameName());

            if (acePlayer.getRoleType() == RoleType.PION) {

                if (AcesUHC.getInstance().getTeamManager().isAsAlive(acePlayer.getTeam())) {
                    respawnPion(acePlayer);
                    return;
                }

            }
            acePlayer.kill();
            return;

        }
    }


    private void respawnPion(AcePlayer acePlayer) {

        Player player = acePlayer.getPlayer();
        player.getPlayer().sendMessage(AcesUHC.prefix + "§aVous allez respawn dans 1 minutes!");
        new BukkitRunnable() {
            @Override
            public void run() {
                acePlayer.revive();
                switch ((int) player.getHealthScale()) {
                    case 20 :
                        player.setHealthScale(14);
                        break;
                    case 14:
                        player.setHealthScale(10);
                        break;
                    default:
                        player.setHealthScale(10);
                        break;

                }
            }
        }.runTaskLater(AcesUHC.getInstance(), 1200);

    }

}
