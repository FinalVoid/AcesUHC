package net.aksyo.player;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;
import net.aksyo.game.teams.JokerTeam;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class AcePlayer {

    private Player player;
    private ITeam team;
    private RoleType roleType;
    private SubRoleType subRoleType;
    private PlayerOption option;
    private RoleOption roleOption;
    private boolean revealed;

    public AcePlayer(Player player, ITeam ITeam, RoleType roleType, SubRoleType subRoleType) {
        this.player = player;
        this.roleType = roleType;
        this.team = roleType.get().isJoker() ? JokerTeam.getInstance() : ITeam;
        this.subRoleType = subRoleType;
        option = PlayerOption.PLAYER;
        roleOption = RoleOption.ROLE;
    }

    public Player getPlayer() {
        return player;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public ITeam getTeam() {
        return team;
    }

    public SubRoleType getSubRoleType() {
        return subRoleType;
    }

    public boolean hasOption(PlayerOption option) {
        return option == this.option;
    }

    public void kill() {
        option = PlayerOption.SPECTATOR;
        player.setGameMode(GameMode.SPECTATOR);
        AcesUHC.getInstance().getTeamManager().killPlayer(this);
    }

    public boolean revive() {

        if (AcesUHC.getInstance().getTeamManager().isPlayerDead(this) && option == PlayerOption.SPECTATOR) {
            AcesUHC.getInstance().getGameManager().getImmunePlayers().add(player);
            int x = new Random().nextInt(125), z = new Random().nextInt(125);
            player.teleport(new Location(player.getWorld(), x, 200, z));
            return AcesUHC.getInstance().getTeamManager().revivePlayer(this);
        }
        return false;
    }

    public void revealSubRole() {

        if (hasSubRole()) {
            subRoleType.get().revealAction(this);
            roleOption = RoleOption.SUBROLE;
        }

    }

    public boolean hasSubRole() {
        return subRoleType != SubRoleType.NULL;
    }

    public boolean isRevealed() {
        return roleOption == RoleOption.SUBROLE;
    }
}

enum RoleOption {

    ROLE,
    SUBROLE;

}
