package net.aksyo.command;

import net.aksyo.AcesUHC;
import net.aksyo.player.AcePlayer;
import net.aksyo.utils.GUI;
import net.aksyo.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class AdminCommand extends AceCommand {

    protected GUI adminGUI;

    public AdminCommand() {
        super("admin", "Permet de gerer la game", true);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        adminGUI = new GUI(AcesUHC.getInstance(), "§bAdmin", 6);

        adminGUI.setItem(3, new ItemBuilder(Material.BREWING_STAND).setName("§aRoles").create(), ((player1, inventoryClickEvent) -> {

            GUI gui = new GUI(AcesUHC.getInstance(), "§2Roles", 8);
            int slot = 0;

            ItemStack item = new ItemStack(Material.DIAMOND);
            ItemMeta meta = item.getItemMeta();

            for (AcePlayer acePlayer : AcesUHC.getInstance().getTeamManager().getAcePlayers()) {
                meta.setDisplayName("§b" + acePlayer.getPlayer().getName());
                meta.setLore(Arrays.asList(" ",
                        "§bTeam : §e" + acePlayer.getTeam().getName(),
                        "§bRole : §e" + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName())));
                item.setItemMeta(meta);
                gui.setItem(slot, item, (target, event) -> {
                    player.closeInventory();
                    player.sendMessage(" ");
                    player.sendMessage("§bTeam : §e" + acePlayer.getTeam().getName());
                    player.sendMessage("§bRole : §e" + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName()));
                    acePlayer.getPlayer().sendMessage("§cTest");

                });


                slot++;

            }

            gui.setItem(63, new ItemBuilder(Material.ARROW).setName("§cReturn").create(), (target, event) -> {
                player.closeInventory();
                gui.open(player);
            });

            gui.setLocked(true);
            gui.open(player);

        }));

        adminGUI.setItem(6, new ItemBuilder(Material.SKULL).setName("§aJoueurs Morts").create(), ((player1, inventoryClickEvent) -> {
            GUI gui = new GUI(AcesUHC.getInstance(), "§3Player Morts", 6);
            int slot = 0;

            ItemStack item = new ItemStack(Material.COAL);
            ItemMeta meta = item.getItemMeta();

            for (AcePlayer acePlayer : AcesUHC.getInstance().getTeamManager().getDeadPlayers()) {
                meta.setDisplayName("§b" + acePlayer.getPlayer().getName());
                meta.setLore(Arrays.asList(" ",
                        "§bTeam : §e" + acePlayer.getTeam().getName(),
                        "§bRole : §e" + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName())));
                item.setItemMeta(meta);
                gui.setItem(slot, item, (target, event) -> {
                    player.closeInventory();
                    player.sendMessage("§c" + acePlayer.getPlayer().getName() + " est mort comme un nulos");

                });

                slot++;
            }

            gui.setLocked(true);
            gui.open(player);
        }));

    }

}
