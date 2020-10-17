package net.aksyo.game.managers;

import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;
import net.aksyo.game.roles.teams.*;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TeamManager {

    private Map<Team, HashSet<AcePlayer>> TEAMS = new HashMap<>();

    public TeamManager() {
        TEAMS.put(PiquesTeam.getInstance(), new HashSet<>());
        TEAMS.put(TrefleTeam.getInstance(), new HashSet<>());
        TEAMS.put(CarreauxTeam.getInstance(), new HashSet<>());
        TEAMS.put(CoeurTeam.getInstance(), new HashSet<>());
        TEAMS.put(JokerTeam.getInstance(), new HashSet<>());
    }

    public void setTeam(Player player, Team team, RoleType roleType) {
        TEAMS.get(team).add(new AcePlayer(player, team, roleType));
    }





}
