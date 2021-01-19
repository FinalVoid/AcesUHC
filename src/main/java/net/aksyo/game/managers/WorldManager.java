package net.aksyo.game.managers;

import net.aksyo.AcesUHC;
import net.aksyo.game.GameOption;
import net.aksyo.game.roles.ITeam;
import net.aksyo.game.teams.PiquesTeam;
import net.aksyo.player.AcePlayer;
import net.aksyo.utils.BasicUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockCanBuildEvent;

import java.util.*;

public class WorldManager {

    private List<Cage> CAGES = new ArrayList<>();
    private List<Location> POINTS = new ArrayList<>();

    public World world;

    protected TeamManager tManager = AcesUHC.getInstance().getTeamManager();

    private Location center;
    private int width;
    private WorldBorder border;

    public WorldManager(World world) {
        this.world = world;
    }

    public void initializeMap(Location center, int width) {

        this.center = center;
        this.width = width;
    }

    public void teleportPlayers(GameMode gameMode) {

        List<Player> playersList = BasicUtils.getGameStartingPlayers(gameMode);
        Collections.shuffle(playersList);

        for (double angle = 0, inc = 360 / playersList.size(); angle < 360; angle += inc) {
            double x = Math.sin(angle) * (width - 12);
            double z = Math.cos(angle) * (width - 12);
            if (playersList.size() > 0) {
                Cage cage = new Cage(center.clone().add(x, 3, z));
                cage.setSoloCage();
                CAGES.add(cage);
                playersList.remove(0).teleport(center.clone().add(x + 0.5, 3, z + 0.5));
            }
        }

    }

    public void teleportTeams() {

        Map<ITeam, HashSet<AcePlayer>> playerList = tManager.getTeamAndMembers();
        int i = 0;

        for (double angle = 0, inc = 360 / tManager.getTeams().length; angle < 360; angle += inc) {

            ITeam team = tManager.getTeams()[i];
            int teamSize = playerList.get(team).size();

            double x = Math.sin(angle) * (width - 18);
            double z = Math.cos(angle) * (width - 18);
            Cage cage = new Cage(center.clone().add(x, 3, z));
            cage.setTeamCage(teamSize);
            CAGES.add(cage);

            int xCoord = new Random().nextInt(teamSize + teamSize / 2) - teamSize * 2, zCoord = new Random().nextInt(teamSize + teamSize / 2) - teamSize * 2;

            for (AcePlayer acePlayer : playerList.get(team)) {

                if (playerList.get(team).size() > 0) {
                    acePlayer.getPlayer().teleport(cage.getCageCenter().clone().add(xCoord, 0, zCoord));
                    playerList.get(team).remove(acePlayer);
                }

            }
            i++;
        }

    }


    public void removeCages(GameOption gameOption) {

        if (gameOption == GameOption.TEAMSPAWN) {
            CAGES.forEach(Cage::removeTeamCage);
            return;
        }

        CAGES.forEach(Cage::removeSoloCage);

    }

    public void createWorldBorder(World world) {
        this.world = world;
        this.border = world.getWorldBorder();

        border.setCenter(center);
        border.setSize(width * 2);

        border.setWarningDistance(5);
    }

    public void deactivateBorder() {

        world.getWorldBorder().reset();

    }

    //Speed in block per second
    public void startBorderShrink(int finalWidth, double speed) {

        double time = Math.floor((width - finalWidth) / 2);

        border.setSize(finalWidth, (long) time);

    }

    private void loadPlayerChunck(int x, int z) {

        for (int i = -64; i < 64; i += 16) {
            world.getChunkAt(x + i , z).load();
            for (int j = -64; j < 64; j += 16) {
                world.getChunkAt(x, z + j).load();
            }
        }

    }

}

class Cage {

    private Location cageCenter;
    private int width;

    public Cage(Location cageCenter) {
        this.cageCenter = cageCenter;
    }

    public void setSoloCage() {

        cageCenter.subtract(0, 1, 0).getBlock().setType(Material.IRON_BLOCK);
        cageCenter.clone().add(1, 0, 0).getBlock().setType(Material.IRON_BLOCK);
        cageCenter.clone().add(0, 0, 1).getBlock().setType(Material.IRON_BLOCK);
        cageCenter.clone().subtract(0,0, 1).getBlock().setType(Material.IRON_BLOCK);
        cageCenter.clone().subtract(1,0, 0).getBlock().setType(Material.IRON_BLOCK);

    }

    public void removeSoloCage() {
        cageCenter.getBlock().setType(Material.AIR);
        cageCenter.clone().add(1, 0, 0).getBlock().setType(Material.AIR);
        cageCenter.clone().add(0, 0, 1).getBlock().setType(Material.AIR);
        cageCenter.clone().subtract(0,0, 1).getBlock().setType(Material.AIR);
        cageCenter.clone().subtract(1,0, 0).getBlock().setType(Material.AIR);
    }

    public void setTeamCage(int size) {
        this.width = size * 2;
        for (int xCoord = cageCenter.getBlockX() - width; xCoord <= cageCenter.getBlockX() + width; xCoord++) {
            for (int zCoord = cageCenter.getBlockZ() - 6; zCoord <= cageCenter.getBlockZ() + 6; zCoord++) {
                cageCenter.clone().add(xCoord, 0, zCoord).getBlock().setTypeIdAndData(Material.STAINED_GLASS.getId(), (byte) 4, false);
            }

        }

    }

    public void removeTeamCage() {

        for (int xCoord = cageCenter.getBlockX() - width; xCoord <= cageCenter.getBlockX() + width; xCoord++) {
            for (int zCoord = cageCenter.getBlockZ() - 6; zCoord <= cageCenter.getBlockZ() + 6; zCoord++) {
                cageCenter.clone().add(xCoord, 0, zCoord).getBlock().setType(Material.AIR);
            }

        }

    }

    public Location getCageCenter() {
        return cageCenter;
    }
}
