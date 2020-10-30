package net.aksyo.game.managers;

import net.aksyo.AcesUHC;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldManager {

    private List<Cage> CAGES = new ArrayList<>();

    private Location center;
    private int width;
    public World world;
    private WorldBorder border;

    public WorldManager(World world) {
        this.world = world;
    }

    public void initializeMap(Location center, int width) {

        this.center = center;
        this.width = width;
    }

    public void teleportPlayers() {

        List<Player> playersList = new ArrayList<>(Bukkit.getOnlinePlayers());

        for (double angle = 0, inc = 360 / playersList.size(); angle < 360; angle += inc) {
            double x = Math.sin(angle) * (width - 3);
            double z = Math.cos(angle) * (width - 3);
            if (playersList.size() > 0) {
                System.out.println(center.getX() + " " + center.getY());
                Cage cage = new Cage(center.clone().add(x, 3, z));
                cage.setCage();
                CAGES.add(cage);
                playersList.remove(0).teleport(center.clone().add(x + 0.5, 3, z + 0.5));
            }
        }
    }

    public void removeCages() {

        CAGES.forEach(Cage::removeCage);

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

}

class Cage {

    private Location cageCenter;

    public Cage(Location cageCenter) {
        this.cageCenter = cageCenter;
    }

    public void setCage() {

        cageCenter.subtract(0, 1, 0).getBlock().setType(Material.IRON_BLOCK);
        cageCenter.clone().add(1, 0, 0).getBlock().setType(Material.IRON_BLOCK);
        cageCenter.clone().add(0, 0, 1).getBlock().setType(Material.IRON_BLOCK);
        cageCenter.clone().subtract(0,0, 1).getBlock().setType(Material.IRON_BLOCK);
        cageCenter.clone().subtract(1,0, 0).getBlock().setType(Material.IRON_BLOCK);

    }

    public void removeCage() {
        cageCenter.getBlock().setType(Material.AIR);
        cageCenter.clone().add(1, 0, 0).getBlock().setType(Material.AIR);
        cageCenter.clone().add(0, 0, 1).getBlock().setType(Material.AIR);
        cageCenter.clone().subtract(0,0, 1).getBlock().setType(Material.AIR);
        cageCenter.clone().subtract(1,0, 0).getBlock().setType(Material.AIR);
    }



}
