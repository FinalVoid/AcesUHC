package net.aksyo.command.game;

import net.aksyo.AcesUHC;
import net.aksyo.command.AceCommand;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.SubRole;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;
import net.aksyo.game.tasks.MainGameTask;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;

public class JokerPacteCommand extends AceCommand {

    private GameManager gManager = AcesUHC.getInstance().getGameManager();
    private TeamManager tManager = AcesUHC.getInstance().getTeamManager();
    private String prefix = AcesUHC.prefix;

    public JokerPacteCommand() {
        super("pacte", "Permet au Pion Fourbe de faire le pacte du Joker", false);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        AcePlayer acePlayer = AcesUHC.getInstance().getTeamManager().getAcePlayer(player);

        if (acePlayer != null) {

            if (acePlayer.hasSubRole() && acePlayer.getSubRoleType() == SubRoleType.PIONFOURBE) {

                if (tManager.getDeadPlayers().stream().anyMatch(a -> a.getRoleType() == RoleType.JOKER)) {
                    player.sendMessage(prefix + "§cLe Joker est mort");
                    System.out.println("Joker mort " + player.getName());
                    return;
                }

                if (gManager.isJokerPacte() || !tManager.getPactePlayers().contains(acePlayer)) {

                    System.out.println("Contains Player in pact : " + tManager.getPactePlayers().contains(acePlayer));
                    tManager.getPactePlayers().add(acePlayer);
                    tManager.applyJokerPacte(acePlayer);

                    System.out.println("Joker pacte " + player.getName());

                } else {
                    player.sendMessage(prefix + "§cVous ne pouvez pas passer le pacte du Joker car " + (MainGameTask.index < 1200 ? "§cl'ouverture des propositions se fait a 20 minutes de jeux" : "§cvous avez deja passer le pacte"));
                }

            } else {
                player.sendMessage(prefix + "§cVous ne pouvez pas passer le pacte du Joker car vous n'etes pas un Pion Fourbe");
            }

        }

    }
}
