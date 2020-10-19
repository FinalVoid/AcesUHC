package net.aksyo.player;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;
import net.aksyo.game.teams.JokerTeam;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class AcePlayer {

    private Player player;
    private RoleType roleType;
    private Team team;
    private PlayerOption option;

    public AcePlayer(Player player, Team team, RoleType roleType) {
        this.player = player;
        this.roleType = roleType;
        this.team = roleType.get().isJoker() ? JokerTeam.getInstance() : team;
        option = PlayerOption.PLAYER;
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
}
