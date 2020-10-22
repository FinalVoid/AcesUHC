package net.aksyo.player;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;
import net.aksyo.game.teams.JokerTeam;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class AcePlayer {

    private Player player;
    private Team team;
    private RoleType roleType;
    private SubRoleType subRoleType;
    private PlayerOption option;
    private RoleOption roleOption;
    private boolean revealed;

    public AcePlayer(Player player, Team team, RoleType roleType, SubRoleType subRoleType) {
        this.player = player;
        this.roleType = roleType;
        this.team = roleType.get().isJoker() ? JokerTeam.getInstance() : team;
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

    public Team getTeam() {
        return team;
    }

    public boolean isOption(PlayerOption option) {
        return option == this.option;
    }

    public void kill() {
        option = PlayerOption.SPECTATOR;
        player.setGameMode(GameMode.SPECTATOR);
    }

    public boolean revive() {

        if (AcesUHC.getInstance().getTeamManager().isPlayerDead(this) && option == PlayerOption.SPECTATOR) {
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
