package net.aksyo.command;

import net.aksyo.AcesUHC;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.roles.Team;
import net.aksyo.player.AcePlayer;
import net.aksyo.utils.GUI;
import net.aksyo.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Collections;
import java.util.stream.Collectors;

public class DebugCommand extends AceCommand {

    private TeamManager tManager = AcesUHC.getInstance().getTeamManager();

    public DebugCommand() {
        super("debug", "Active le debug", true);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (args.length == 1) {
            if(AcesUHC.getInstance().getGameManager().isDebug()) return;
            player.sendMessage("§bDebug is now activated!");
            AcesUHC.getInstance().getGameManager().activateDebug();
            return;
        }

        if (args.length > 1) {

            switch (args[1]) {

                case "distribute":
                    executeDistribution(player);
                    break;

                case "teams":
                    teamCheckGui(player);
                    break;

                case "roles":
                    roleGui(player);
                    break;
            }

        }

    }

    private void executeDistribution(Player player) {
        player.sendMessage("§aDistribution faite!");
        tManager.distribute(Bukkit.getOnlinePlayers().stream().collect(Collectors.toList()));
    }

    private void teamCheckGui(Player player) {

        GUI gui = new GUI(AcesUHC.getInstance(), "§2Teams", 1);
        int slot = 0;

        for (Team team : tManager.getTeams()) {
            gui.setItem(slot, new ItemBuilder(Material.PAINTING).setName("Team").lore(" ",
                    "§3Game Name : §r" + team.getGameName()).create(), (target, inventoryClickEvent) -> {});
            slot += 2;
        }

        gui.setLocked(true);
        gui.open(player);

    }

    private void roleGui(Player player) {

        GUI gui = new GUI(AcesUHC.getInstance(), "§2Roles", 8);
        int slot = 0;

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        for (AcePlayer acePlayer : tManager.getAcePlayers()) {
            meta.setOwner(acePlayer.getPlayer().getName());
            gui.setItem(slot, new ItemBuilder(skull).setName("§3" + acePlayer.getPlayer().getDisplayName()).lore(
                    " ",
                    "§bTeam : §e" + acePlayer.getTeam().getName(),
                    "§bRole : §e" + acePlayer.getRoleType().get().getName()
            ).create(), (target, inventoryClickEvent) -> {
                player.closeInventory();
                player.sendMessage(" ");
                player.sendMessage("§bTeam : §e" + acePlayer.getTeam().getName());
                player.sendMessage("§bRole : §e" + acePlayer.getRoleType().get().getName());

            });

            slot++;

        }

        gui.setLocked(true);
        gui.open(player);

    }
}
