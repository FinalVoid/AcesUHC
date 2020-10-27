package net.aksyo.command;

import com.google.gson.internal.$Gson$Preconditions;
import net.aksyo.AcesUHC;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.roles.ITeam;
import net.aksyo.player.AcePlayer;
import net.aksyo.utils.GUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DebugCommand extends AceCommand {

    private TeamManager tManager = AcesUHC.getInstance().getTeamManager();
    private String admin = AcesUHC.adminPrefix;

    public DebugCommand() {
        super("debug", "Active le debug", true);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (args.length == 1) {
            if(AcesUHC.getInstance().getGameManager().isDebug()) return;
            player.sendMessage(admin + "§bDebug is now activated!");
            AcesUHC.getInstance().getGameManager().activateDebug();
            return;
        }

        if (args.length > 1) {

            switch (args[1]) {

                case "distribute":
                    executeDistribution(player);
                    break;

                case "teams":
                    player.sendMessage(admin + "§aOuverture du GUI des equipes");
                    teamCheckGui(player);
                    break;

                case "roles":
                    player.sendMessage(admin + "§aOuverture du GUI des players");
                    roleGui(player);
                    break;

                case "reveal":
                    revealMessages(player);
                    break;

                case "tmanager":
                    player.sendMessage(admin + "§bCheck console please");
                    checkTManager();
                    break;

                case "dead":
                    player.sendMessage("§aOuverture du GUI des players §cMORTES (les nulos)");
                    deadPlayersGUI(player);
                    break;
            }

        }

    }

    private void executeDistribution(Player player) {
        player.sendMessage(admin + "§aDistribution lancé!");
        new BukkitRunnable() {
            @Override
            public void run() {
                boolean executed = tManager.distribute(Bukkit.getOnlinePlayers().stream().collect(Collectors.toList()));
                player.sendMessage(admin + (executed ? "§aDistribution terminé!" : "§cDistribution failed!"));

            }
        }.runTaskLater(AcesUHC.getInstance(), 40);
    }

    private void teamCheckGui(Player player) {

        GUI gui = new GUI(AcesUHC.getInstance(), "§2Teams", 1);
        int slot = 0;
        ItemStack item = new ItemStack(Material.PAINTING);
        ItemMeta meta = item.getItemMeta();

        for (ITeam iTeam : tManager.getTeams()) {
            System.out.println(iTeam.getGameName());
            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add("§3Game Name : " + iTeam.getGameName());
            lore.add("§3Team Members :");
            for (AcePlayer p : tManager.getTeamMembers(iTeam)) {
                System.out.println(p.getPlayer().getName());
                lore.add("§2" + p.getPlayer().getName() + " §8- §9" + (p.hasSubRole() ? p.getSubRoleType().get().getGameName() : p.getRoleType().get().getGameName()));
            }
            meta.setDisplayName("§3" + iTeam.getName());
            meta.setLore(lore);
            item.setItemMeta(meta);
            gui.setItem(slot, item, (target, inventoryClickEvent) -> {});
            slot += 2;
        }

        gui.setLocked(true);
        gui.open(player);

    }

    private void roleGui(Player player) {

        GUI gui = new GUI(AcesUHC.getInstance(), "§2Roles", 8);
        int slot = 0;

        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();

        System.out.println(tManager.getAcePlayers().size());

        for (AcePlayer acePlayer : tManager.getAcePlayers()) {
            meta.setDisplayName(acePlayer.getPlayer().getName());
            meta.setLore(Arrays.asList(" ",
                    "§bTeam : §e" + acePlayer.getTeam().getName(),
                    "§bRole : §e" + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName())));
            item.setItemMeta(meta);
            gui.setItem(slot, item, (target, inventoryClickEvent) -> {
                player.closeInventory();
                player.sendMessage(" ");
                player.sendMessage("§bTeam : §e" + acePlayer.getTeam().getName());
                player.sendMessage("§bRole : §e" + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName()));

            });

            slot++;

        }

        gui.setLocked(true);
        gui.open(player);

    }

    private void revealMessages(Player player) {
        player.sendMessage(admin + "§bReveal des roles a tous le monde!");
        new BukkitRunnable() {
            @Override
            public void run() {
                tManager.revealRoles();
            }
        }.runTaskLater(AcesUHC.getInstance(), 40);
    }

    private void checkTManager() {

        for (int i = 0; i < tManager.getTeams().length; i++) {
            System.out.println(tManager.getTeams()[i].getGameName());
        }

    }

    private void deadPlayersGUI(Player player) {
        GUI gui = new GUI(AcesUHC.getInstance(), "§3Player Morts", 6);
        int slot = 0;

        ItemStack item = new ItemStack(Material.COAL);
        ItemMeta meta = item.getItemMeta();

        for (AcePlayer acePlayer : tManager.getDeadPlayers()) {
            meta.setDisplayName(acePlayer.getPlayer().getName());
            meta.setLore(Arrays.asList(" ",
                    "§bTeam : §e" + acePlayer.getTeam().getName(),
                    "§bRole : §e" + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName())));
            item.setItemMeta(meta);
            gui.setItem(slot, item, (target, inventoryClickEvent) -> {
                player.closeInventory();
                player.sendMessage("§c" + acePlayer.getPlayer().getName() + " est mort comme un nulos");

            });

            slot++;
        }

        gui.setLocked(true);
        gui.open(player);
    }
}
