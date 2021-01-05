package net.aksyo.listeners;

import com.google.gson.internal.$Gson$Preconditions;
import net.aksyo.AcesUHC;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.roles.ITeam;
import net.aksyo.game.roles.RoleType;
import net.aksyo.player.AcePlayer;
import net.aksyo.player.PlayerOption;
import net.aksyo.utils.BasicUtils;
import net.aksyo.utils.Pair;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class PlayerDeathListener implements Listener {

    protected TeamManager tManager = AcesUHC.getInstance().getTeamManager();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        AcePlayer acePlayer = tManager.getAcePlayer(player);
        //AcePlayer killer = tManager.getAcePlayer(event.getEntity().getKiller());
        event.setDeathMessage(" ");

        if (acePlayer != null) {

            BasicUtils.silentBroadcast("§c" + player.getName() + "§e est mort! C'etais un : " + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName())
            + " §eIl faisait partie de l'equipe des : " + acePlayer.getTeam().getGameName());

            acePlayer.kill();

            Pair<Boolean, ITeam> possibleVictory = checkForVictory();
            if (possibleVictory.getRight()) {
                AcesUHC.getInstance().getGameManager().setGameState(GameState.ENDING);
                tManager.playVictory(possibleVictory.getLeft());
            }

            if (acePlayer.getRoleType() == RoleType.PION) {

                if (tManager.isAsAlive(acePlayer.getTeam()) && !possibleVictory.getRight()) {
                    if (!acePlayer.hasSubRole()) {
                       respawnPion(acePlayer);
                    }
                    return;
                }
            }
            return;

        }
    }


    private void respawnPion(AcePlayer acePlayer) {

        Player player = acePlayer.getPlayer();
        player.sendMessage(AcesUHC.prefix + "§aVous allez respawn dans 1 minutes!");
        new BukkitRunnable() {
            @Override
            public void run() {
                acePlayer.revive();
                switch ((int) player.getHealthScale()) {
                    case 20 :
                        player.setHealthScale(16);
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

    private Pair<Boolean, ITeam> checkForVictory() {

        ITeam reference = tManager.getAlivePlayers().get(new Random().nextInt(tManager.getAlivePlayers().size())).getTeam();

        for (AcePlayer player : tManager.getAlivePlayers()) {

            if (player.getTeam() != reference) {
                return Pair.of(false, null);
            }

        }

        return Pair.of(true, reference);

    }

}
