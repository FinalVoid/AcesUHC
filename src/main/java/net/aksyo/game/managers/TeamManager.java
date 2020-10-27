package net.aksyo.game.managers;

import com.google.gson.internal.$Gson$Preconditions;
import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;
import net.aksyo.game.teams.*;
import net.aksyo.player.AcePlayer;
import net.aksyo.player.PlayerOption;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TeamManager {

    private Map<ITeam, HashSet<AcePlayer>> TEAMS = new HashMap<>();
    private LinkedList<AcePlayer> deadPlayers = new LinkedList<>();

    public TeamManager() {
        TEAMS.put(PiquesTeam.getInstance(), new HashSet<AcePlayer>());
        TEAMS.put(TrefleTeam.getInstance(), new HashSet<AcePlayer>());
        TEAMS.put(CarreauxTeam.getInstance(), new HashSet<AcePlayer>());
        TEAMS.put(CoeurTeam.getInstance(), new HashSet<AcePlayer>());
        TEAMS.put(JokerTeam.getInstance(), new HashSet<AcePlayer>());
    }

    public boolean setTeam(Player player, ITeam team, RoleType roleType, SubRoleType subRoleType) {
        return TEAMS.get(team).add(new AcePlayer(player, team, roleType, subRoleType));
    }

    public Set<AcePlayer> getAcePlayers() {

        Set<AcePlayer> set = new HashSet<>();

        for (HashSet<AcePlayer> t : TEAMS.values()) {
            set.addAll(t);
        }

        return set;
    }

    public ITeam[] getTeams() {
        ITeam[] teams = new ITeam[TEAMS.keySet().size()];
        int j = 0;
        for (ITeam ITeam : TEAMS.keySet()) {
            teams[j] = ITeam;
            j++;
        }

        return teams;
    }

    public AcePlayer getAcePlayer(Player player) {
        for(Set<AcePlayer> list : TEAMS.values()) {
            Optional<AcePlayer> optional = list.stream().filter(acePlayer -> acePlayer.getPlayer().getUniqueId().compareTo(player.getUniqueId()) == 0).findFirst();
            if(optional.isPresent()) {
                return optional.get();
            }
        }
        return null;
    }

    public Set<AcePlayer> getTeamMembers(ITeam team) {
        return TEAMS.get(team);
    }

    public boolean killPlayer(AcePlayer player) {
        return deadPlayers.add(player);
    }

    public boolean isPlayerDead(AcePlayer player) {
        return deadPlayers.contains(player);
    }

    public boolean revivePlayer(AcePlayer player) {
        player.revive();
        return deadPlayers.remove(player);
    }

    public LinkedList<AcePlayer> getDeadPlayers() {
        return deadPlayers;
    }

    public boolean distribute(List<Player> players) {

        if ((players.size() - 1) % 4 == 0) {

            System.out.println("Distributing Roles!");

            final int times = (players.size() - 1) / 4;
            System.out.println("Times : " + times);

            int randomJoker = randomValue(players.size());
            setTeam(players.get(randomJoker), JokerTeam.getInstance(), RoleType.JOKER, SubRoleType.NULL);
            System.out.println("Joker set to " + players.get(randomJoker).getName() + " Random Value : " + randomJoker + " List Size : " + players.size());
            players.remove(randomJoker);
            System.out.println("New Size : " + getAcePlayers().size());

            for (ITeam ITeam : AcesUHC.getInstance().getTeamManager().getTeams()) {

                if (ITeam instanceof JokerTeam) {
                    continue;
                }

                boolean isAsSet = false, isPionFourbeSet = false;
                System.out.println("Team : " + ITeam.getName());
                for (int i = 0; i < times; i++) {
                    int playerIndex = randomValue(players.size());
                    System.out.println("As Set : " + (isAsSet ? "YES" : "NO") + " PionFourbe : " + (isPionFourbeSet ? "YES" : "NO"));
                    if (!isAsSet) {
                        setTeam(players.get(playerIndex), ITeam, RoleType.AS, SubRoleType.NULL);
                        System.out.println("As set to " + players.get(playerIndex).getName() + " Random Value : " + playerIndex + " List Size : " + players.size());
                        players.remove(playerIndex);
                        System.out.println("New Size : " + getAcePlayers().size());
                        isAsSet = true;
                        continue;
                    } else if (!isPionFourbeSet) {
                        setTeam(players.get(playerIndex), ITeam, RoleType.PION, SubRoleType.PIONFOURBE);
                        System.out.println("Fourbe set to " + players.get(playerIndex).getName() + " Random Value : " + playerIndex + " List Size : " + players.size());
                        players.remove(playerIndex);
                        System.out.println("New Size : " + getAcePlayers().size());
                        isPionFourbeSet = true;
                        continue;
                    }
                    setTeam(players.get(playerIndex), ITeam, RoleType.PION, SubRoleType.NULL);
                    System.out.println("Pion set to " + players.get(playerIndex).getName() + " Random Value : " + playerIndex + " List Size : " + players.size());
                    players.remove(playerIndex);
                    System.out.println("New Size : " + getAcePlayers().size());
                }
            }
            return true;
        }

        System.out.println("Cannot distribute roles");
        return false;


    }

    public AcePlayer getTeamAsPlayer(ITeam team) {

        if (team instanceof JokerTeam) {
            System.out.println("Cannot check for Joker Team");
            return null;
        }

        for (AcePlayer acePlayer : TEAMS.get(team)) {
            if (acePlayer.getRoleType() == RoleType.AS) {
                return acePlayer;
            }
        }

        return null;

    }

    public boolean isAsAlive(ITeam team) {

        if (team instanceof JokerTeam) {
            System.out.println("Cannot check for Joker Team");
            return true;
        }

        for (AcePlayer acePlayer : TEAMS.get(team)) {
            if (acePlayer.getRoleType() == RoleType.AS) {
                if (acePlayer.isOption(PlayerOption.PLAYER)) return true;
            }
        }
        return false;
    }

    public void revealRoles() {


        for (AcePlayer acePlayer : getAcePlayers()) {
            Player player = acePlayer.getPlayer();

            player.playSound(player.getLocation(), Sound.PORTAL_TRAVEL, 1, 1.5f);

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage(" ");
                    player.sendMessage("                         §3§l Révèlation des Roles - " + acePlayer.getTeam().getGameName());
                    player.sendMessage(" ");
                    player.sendMessage(AcesUHC.prefix + "§eVotre role est : §3§l" + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getName() : acePlayer.getRoleType().get().getName()));
                    if (acePlayer.getRoleType() != RoleType.JOKER) {
                        player.sendMessage("     §5§l-  §r§6L'As de votre equipe est : §e" + getTeamAsPlayer(acePlayer.getTeam()).getPlayer().getName());
                    }
                    player.sendMessage("     §5§l-  §r§9" + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getDescription() : acePlayer.getRoleType().get().getDescription()));
                    player.sendMessage(" ");
                    player.sendMessage(AcesUHC.prefix + "§2Tape §b/a role §2pour avoir plus d'information.");
                }
            }.runTaskLater(AcesUHC.getInstance(), 45);

        }

    }

    protected int randomValue(int bound) {

        return new Random().nextInt(bound);

    }

    protected void playRevealSound(Player player) {

        for (int i = 0; i < 2; i++) {
            final float volume = 0.5f + i * 0.2f;
            final int ticks = i * 4;
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.playSound(player.getLocation(), Sound.PORTAL_TRAVEL, 1, volume);
                }
            }.runTaskLater(AcesUHC.getInstance(), ticks);
        }
    }



}
