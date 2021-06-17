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
import org.bukkit.inventory.ItemStack;
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



            acePlayer.getPlayerData().addDeath();
            acePlayer.getPlayerData().stopRecordingTimeLived();

            addPlayerData(event.getEntity().getKiller());

            Pair<Boolean, ITeam> possibleVictory = checkForVictory();
            if (possibleVictory.getRight()) {
                AcesUHC.getInstance().getGameManager().setGameState(GameState.ENDING);
                tManager.playVictory(possibleVictory.getLeft());
            }

            if (acePlayer.getRoleType() == RoleType.PION) {

                if (tManager.isAsAlive(acePlayer.getTeam()) && !possibleVictory.getRight()) {
                    respawnPion(acePlayer);
                    return;
                }
            }

            BasicUtils.silentBroadcast("\n§c" + player.getName() + "§e est mort! C'etais un : " + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName())
                    + " §eIl faisait partie de l'equipe des : " + acePlayer.getTeam().getGameName());

            for (ItemStack itemStack : player.getInventory().getContents()) {
                AcesUHC.getInstance().getWorldManager().world.dropItemNaturally(event.getEntity().getLocation(), itemStack);
            }
            return;

        }
    }

    private void addPlayerData(Player player) {

        AcePlayer acePlayer = tManager.getAcePlayer(player);

        if (acePlayer != null) {
            acePlayer.getPlayerData().addKill();
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
                    case 16:
                        player.setHealthScale(14);
                        break;
                    default:
                        player.setHealthScale(14);
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
