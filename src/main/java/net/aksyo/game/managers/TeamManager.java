package net.aksyo.game.managers;



import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;
import net.aksyo.game.roles.gamesroles.Joker;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;
import net.aksyo.game.teams.*;
import net.aksyo.player.AcePlayer;
import net.aksyo.player.PlayerOption;
import net.aksyo.utils.BasicUtils;
import net.aksyo.utils.LogFormat;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class TeamManager {

    private GameManager gManager = AcesUHC.getInstance().getGameManager();

    private int startingPlayers;

    private Map<ITeam, HashSet<AcePlayer>> TEAMS = new HashMap<>();
    private Map<ITeam, Chest> CHESTS = new HashMap<>();
    private LinkedList<AcePlayer> deadPlayers = new LinkedList<>();
    private HashSet<AcePlayer> pactePlayers = new HashSet<>();
    private HashSet<AcePlayer> saturationPowerPlayers = new HashSet<>();
    private boolean jokerChange = false;

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

    public void setStartingPlayers(GameMode gameMode) {
        this.startingPlayers = BasicUtils.getGameStartingPlayers(gameMode).size();
    }

    public Set<AcePlayer> getAcePlayers() {

        Set<AcePlayer> set = new HashSet<>();

        for (HashSet<AcePlayer> t : TEAMS.values()) {
            set.addAll(t);
            if (t != null) {
                for (AcePlayer acePlayer : t) {
                    System.out.println(acePlayer.getPlayer().getName());
                }
            }
        }

        return set;
    }

    public ITeam getTeamByName(String name) {
        return TEAMS.keySet().stream().filter(t -> t.getName().equalsIgnoreCase(name)).findFirst().get();
    }

    public RoleType getRoleByName(String name) {
        return Arrays.stream(RoleType.values()).filter(r -> r.get().getName().equalsIgnoreCase(name)).findAny().get();
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

    public Map<ITeam, HashSet<AcePlayer>> getTeamAndMembers() {
        return new HashMap<ITeam, HashSet<AcePlayer>>(TEAMS);
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

    public AcePlayer getJoker() {
        return getAcePlayers().stream().filter(p -> p.getRoleType() == RoleType.JOKER).findFirst().get();
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

    public LinkedList<AcePlayer> getDeadPlayers() {
        return deadPlayers;
    }

    public List<AcePlayer> getAlivePlayers() {

        List<AcePlayer> alivePlayes = new ArrayList<>();

        for (AcePlayer player : getAcePlayers()) {
            if (!deadPlayers.contains(player)) {
                alivePlayes.add(player);
            }
        }

        return alivePlayes;

    }

    public HashSet<AcePlayer> getSaturationPowerPlayers() {
        return saturationPowerPlayers;
    }

    public int getAlivePlayersNumber() {

        if (TEAMS.size() == 0 || TEAMS.isEmpty()) {
            return 0;
        }

        return startingPlayers - deadPlayers.size(); //TODO auto calculation

    }

    public boolean distribute(List<Player> players) {

        if ((players.size() - 1) % 4 == 0) {

            if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Distributing roles!");

            final int times = (players.size() - 1) / 4;

            Collections.shuffle(players);

            int randomJoker = randomValue(players.size());
            setTeam(players.get(randomJoker), JokerTeam.getInstance(), RoleType.JOKER, SubRoleType.NULL);
            players.remove(randomJoker);

            for (ITeam ITeam : AcesUHC.getInstance().getTeamManager().getTeams()) {

                if (ITeam instanceof JokerTeam) {
                    continue;
                }

                boolean isAsSet = false, isPionFourbeSet = false;
                for (int i = 0; i < times; i++) {
                    int playerIndex = randomValue(players.size());
                    if (!isAsSet) {
                        setTeam(players.get(playerIndex), ITeam, RoleType.AS, SubRoleType.NULL);
                        players.remove(playerIndex);
                        isAsSet = true;
                        continue;
                    } else if (!isPionFourbeSet) {
                        setTeam(players.get(playerIndex), ITeam, RoleType.PION, SubRoleType.PIONFOURBE);
                        players.remove(playerIndex);
                        isPionFourbeSet = true;
                        continue;
                    }
                    setTeam(players.get(playerIndex), ITeam, RoleType.PION, SubRoleType.NULL);
                    players.remove(playerIndex);
                }
            }
            return true;
        }

        AcesUHC.getInstance().log(LogFormat.ERROR, "Cannot distribute roles");
        return false;


    }

    public AcePlayer getTeamAsPlayer(ITeam team) {

        if (team instanceof JokerTeam) {
            if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Cannot check for joker team");
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
            if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Cannot check for joker team");
            return true;
        }

        for (AcePlayer acePlayer : TEAMS.get(team)) {
            if (acePlayer.getRoleType() == RoleType.AS) {
                if (acePlayer.hasOption(PlayerOption.PLAYER)) return true;
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
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!acePlayer.hasSubRole() && acePlayer.getRoleType() != RoleType.AS) {
                                acePlayer.getRoleType().get().applyPowers().accept(acePlayer);
                            }
                        }
                    }.runTaskLater(AcesUHC.getInstance(), 45);
                }
            }.runTaskLater(AcesUHC.getInstance(), 45);

        }

    }

    public void spawnChests() {

        for (ITeam team : getTeams()) {
            if (team instanceof JokerTeam) continue;

            Chest chest = AcesUHC.getInstance().getChestManager().spawnChest(team);
            CHESTS.put(team, chest);
            if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Spawned Chest");
        }

    }

    public void whisper(int current) {

        for (ITeam team : getTeams()) {

            if (team instanceof JokerTeam) continue;
            if (TEAMS.get(team).size() == 0) continue;

            Location chestLocation = CHESTS.get(team).getLocation();
            String x = String.valueOf(chestLocation.getX()), y = String.valueOf(chestLocation.getY()), z = String.valueOf(chestLocation.getZ());
            String whisper = "§d§lWHISPER §e> §b";
            String[] jam = {x, y, z};

            if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "X " + x + " Y " + y + " Z " + z);

            for (String str : jam) {
                char[] c = str.toCharArray();
                if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Team : " + team.getName() + " Current : " + current + " Length : " + c.length);
                if (current > c.length) {
                    whisper += str;
                    continue;
                }
                for (int i = 0; i < c.length; i++) {
                    if (i >= current) {
                        whisper += "§k" + c[i];
                    }
                    whisper += c[i];
                }
                whisper += "§r §b";
            }
            getTeamAsPlayer(team).getPlayer().sendMessage(whisper);

        }

    }

    public boolean checkChest(ITeam team, Block block) {
        return CHESTS.get(team).getLocation() == block.getLocation();
    }

    public void changeJokerTeam(ITeam team) {

        if (!jokerChange) {
            TEAMS.get(JokerTeam.getInstance()).forEach(p ->{
                p.getPlayer().sendMessage(AcesUHC.prefix + team.getGameName() + "§a a trouvé le pouvoir de l'As, vous faites desormais partie de leur équipe.");
            });
            TEAMS.get(team).addAll(TEAMS.get(JokerTeam.getInstance()));
            AtomicReference<String> jokers = new AtomicReference<>("");
            TEAMS.get(JokerTeam.getInstance()).forEach(p -> jokers.set(jokers.get() + p.getPlayer().getName() + ", "));
            TEAMS.get(team).forEach(p -> p.getPlayer().sendMessage(jokers.get() + " §aa rejoins votre equipe!"));
            TEAMS.get(JokerTeam.getInstance()).clear();

            jokerChange = true;
        }

    }

    public void playVictory(ITeam team) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            playVictorySound(player);
        }

        BasicUtils.silentBroadcast(" ");
        BasicUtils.silentBroadcast("§aLa team des vainqueurs est : " + team.getGameName()); //TODO Most kills announcement
        BasicUtils.silentBroadcast(" ");

        for (AcePlayer acePlayer : getTeamMembers(team)) {

            for (int i = 0; i < 10; i++) {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        int r = new Random().nextInt(255), g = new Random().nextInt(255), b = new Random().nextInt(255);

                        Firework f = (Firework) acePlayer.getPlayer().getWorld().spawn(acePlayer.getPlayer().getLocation(), Firework.class);

                        FireworkMeta fm = f.getFireworkMeta();
                        fm.addEffect(FireworkEffect.builder()
                                .flicker(false)
                                .trail(true)
                                .with(FireworkEffect.Type.BALL_LARGE)
                                .withColor(Color.fromRGB(r, g, b))
                                .withFade(Color.WHITE)
                                .build());
                        fm.setPower(new Random().nextInt(3));
                        f.setFireworkMeta(fm);

                    }
                }.runTaskLater(AcesUHC.getInstance(), 20 * i);
            }

        }

    }

    public void applyJokerPacte(AcePlayer acePlayer) {

        Player player = acePlayer.getPlayer();
        Location location = player.getLocation();
        DecimalFormat df = new DecimalFormat("###.###");

        getJoker().getPlayer().sendMessage(AcesUHC.prefix + "§b" + player.getName() + " §e a passé un pacte avec vous. Voici ses coordonnées : \n" +
                "§6X : §9" + df.format(location.getX()) + " §6Y : §9" + df.format(location.getY()) + " §6Z : §9" + df.format(location.getZ()));

        int r = new Random().nextInt(3);

        switch (r) {
            case 0 :
                player.setHealthScale(10);
                player.sendMessage(AcesUHC.prefix + "§cVous avez perdu 5 coeurs a cause du pacte!");
                if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Pion Fourbe value : " + r + " Player : " + player.getName());
                break;
            case 1:
                player.setHealthScale(20);
                player.sendMessage(AcesUHC.prefix + "§eIl ne s'est rien passé");
                if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Pion Fourbe value : " + r + " Player : " + player.getName());
                break;
            case 2:
                player.setHealthScale(30);
                player.sendMessage(AcesUHC.prefix + "§aVous avez gagner 5 coeurs grace au pacte!");
                if (gManager.isDebug()) AcesUHC.getInstance().log(LogFormat.DEBUG, "Pion Fourbe value : " + r + " Player : " + player.getName());
                break;

        }


    }

    public HashSet<AcePlayer> getPactePlayers() {
        return pactePlayers;
    }

    protected int randomValue(int bound) {

        return new Random().nextInt(bound);

    }

    protected void playSound(Player player) {

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

    protected void playVictorySound(Player player) {
        player.playSound(player.getLocation(), Sound.ENDERMAN_DEATH, 1f, 2f);
    }



}
