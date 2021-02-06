package net.aksyo.player;

import net.aksyo.AcesUHC;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.roles.ITeam;
import net.aksyo.game.roles.gamesroles.subroles.SubRoleType;
import net.aksyo.game.teams.JokerTeam;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;

public class AcePlayer {

    private UUID uuid;
    private Player player;

    private ITeam team;
    private RoleType roleType;
    private SubRoleType subRoleType;
    private PlayerOption option;
    private RoleOption roleOption;

    private PlayerData playerData;

    private boolean revealed;

    public AcePlayer(Player player, ITeam ITeam, RoleType roleType, SubRoleType subRoleType) {
        this.uuid = player.getUniqueId();
        this.player = player;
        this.roleType = roleType;
        this.team = roleType.get().isJoker() ? JokerTeam.getInstance() : ITeam;
        this.subRoleType = subRoleType;
        option = PlayerOption.PLAYER;
        roleOption = RoleOption.ROLE;

        playerData = new PlayerData(this);

    }

    public void updatePlayerCache() {
        this.player = Bukkit.getOnlinePlayers().stream().filter(p -> p.getUniqueId().compareTo(uuid) == 0).findFirst().get();

    }

    public UUID getUUID() {
        return uuid;
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
            player.setGameMode(GameMode.SURVIVAL);
            playerData.startRecordingTimeLived();
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (AcesUHC.getInstance().getGameManager().getImmunePlayers().contains(player)) {
                        AcesUHC.getInstance().getGameManager().getImmunePlayers().remove(player);
                    }
                }
            }.runTaskLater(AcesUHC.getInstance(), 100);
            return AcesUHC.getInstance().getTeamManager().getDeadPlayers().remove(this);
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

    public PlayerData getPlayerData() {
        return playerData;
    }
}

enum RoleOption {

    ROLE,
    SUBROLE;

}
