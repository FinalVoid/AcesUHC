package net.aksyo.scoreboard.providers;

import net.aksyo.AcesUHC;
import net.aksyo.game.tasks.MainGameTask;
import net.aksyo.player.AcePlayer;
import net.aksyo.scoreboard.ScoreboardProvider;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameProvider extends ScoreboardProvider {

    private Player player;

    public GameProvider(Player player) {
        this.player = player;
    }

    private int lastSwitchTick;
    private boolean state;

    @Override
    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        /*if(lastSwitchTick == 0 && AcesUHC.getInstance().getServer().getti != 0) lastSwitchTick = MinecraftServer.currentTick;
        if(MinecraftServer.currentTick - lastSwitchTick > 40){
            state = !state;
            lastSwitchTick = MinecraftServer.currentTick;
        } */
        //for (int i = 0; i < 6; i++) {
        AcePlayer acePlayer = AcesUHC.getInstance().getTeamManager().getAcePlayer(player);
        lines.add(" ");
        Date date = new Date((long) (MainGameTask.index) * 1000);
        String dateFormat = new SimpleDateFormat("hh:mm:ss").format(date);

        if (acePlayer != null){

            lines.add("§6Equipe : " + acePlayer.getTeam().getGameName());
            lines.add("§6Role : " + (acePlayer.hasSubRole() ? acePlayer.getSubRoleType().get().getGameName() : acePlayer.getRoleType().get().getGameName()));
            lines.add(" ");
        }

        lines.add("§6Alive players : §3" + AcesUHC.getInstance().getTeamManager().getAlivePlayersNumber());
        lines.add(" ");
        lines.add("§6Temps : §3" + dateFormat);

        //}

        return lines;
    }

    public Player getPlayer() {
        return player;
    }

}
