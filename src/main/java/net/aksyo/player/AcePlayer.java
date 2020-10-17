package net.aksyo.player;

import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;
import net.aksyo.game.roles.teams.JokerTeam;
import org.bukkit.entity.Player;

public class AcePlayer {

    private Player player;
    private RoleType roleType;
    private Team team;

    public AcePlayer(Player player, Team team, RoleType roleType) {
        this.player = player;
        this.roleType = roleType;
        this.team = roleType.get().isJoker() ? JokerTeam.getInstance() : team;
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
}
