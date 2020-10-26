package net.aksyo.game.managers;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.Team;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;
import net.aksyo.game.teams.*;
import net.aksyo.player.AcePlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class TeamManager {

    private Map<Team, HashSet<AcePlayer>> TEAMS = new HashMap<>();
    private LinkedList<AcePlayer> deadPlayers = new LinkedList<>();

    public TeamManager() {
        TEAMS.put(PiquesTeam.getInstance(), new HashSet<>());
        TEAMS.put(TrefleTeam.getInstance(), new HashSet<>());
        TEAMS.put(CarreauxTeam.getInstance(), new HashSet<>());
        TEAMS.put(CoeurTeam.getInstance(), new HashSet<>());
        TEAMS.put(JokerTeam.getInstance(), new HashSet<>());
    }

    public AcePlayer[] getAcePlayers() {
        int count = TEAMS.values().stream().mapToInt(HashSet::size).sum();

        AcePlayer[] players = new AcePlayer[count];

        int j = 0;

        for(Map.Entry<Team, HashSet<AcePlayer>> entry : TEAMS.entrySet()) {
            for(AcePlayer p : entry.getValue()) {
                players[j] = p;
                j++;
            }
        }

        return players;
    }

    public Team[] getTeams() {
        Team[] teams = new Team[TEAMS.keySet().size()];

        int j = 0;

        for (Team team : TEAMS.keySet()) {
            teams[j] = team;
            j++;
        }

        return teams;
    }

    /**
     *
     * @return An array of the principal teams (Piques, Trefle, Carreaux, Coeur)
     */
    public Team[] getPrincipalTeams() {
        Set<Team> teamSet = TEAMS.keySet();
        teamSet.remove(JokerTeam.getInstance());
        Team[] teams = new Team[teamSet.size()];

        int j = 0;

        for (Team team : teamSet) {
            teams[j] = team;
            j++;
        }

        return teams;
    }

    public void setTeam(Player player, Team team, RoleType roleType, SubRoleType subRoleType) {
        TEAMS.get(team).add(new AcePlayer(player, team, roleType, subRoleType));
    }

    public boolean isPlayerDead(AcePlayer player) {
        return deadPlayers.contains(player);
    }

    public boolean revivePlayer(AcePlayer player) {
        player.revive();
        return deadPlayers.remove(player);
    }

    public void distribute(List<Player> players) {

        if ((players.size() - 1) % 4 == 0) {

            int randomJoker = randomValue(players.size());
            setTeam(players.get(randomJoker), JokerTeam.getInstance(), RoleType.JOKER, SubRoleType.NULL);
            players.remove(randomJoker);

            for (Team team : AcesUHC.getInstance().getTeamManager().getPrincipalTeams()) {
                boolean isAsSet = false, isPionFourbeSet = false;
                for (int i = 0; i < players.size() / 4; i++) {
                    int playerIndex = randomValue(players.size());
                    if (!isAsSet) {
                        setTeam(players.get(playerIndex), team, RoleType.AS, SubRoleType.NULL);
                        players.remove(playerIndex);
                        isAsSet = true;
                        continue;
                    } else if (!isPionFourbeSet) {
                        setTeam(players.get(playerIndex), team, RoleType.PION, SubRoleType.PIONFOURBE);
                        players.remove(playerIndex);
                        isPionFourbeSet = true;
                        continue;
                    }
                    setTeam(players.get(playerIndex), team, RoleType.PION, SubRoleType.NULL);
                    players.remove(playerIndex);
                }
            }

        } else {
            System.out.println("Cannot distribute roles");
        }

    }

    protected int randomValue(int bound) {

        return new Random().nextInt(bound);

    }



}
