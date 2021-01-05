package net.aksyo.player;

import java.util.UUID;

public class PlayerData {


    private final AcePlayer acePlayer;

    private int kills;
    private int deaths;
    private int blockedMined;
    private long timeLived;

    private long previous;

    public PlayerData(final AcePlayer acePlayer) {
        this.acePlayer = acePlayer;
    }

    public void addKill() {
        kills++;
    }

    public void addDeath() {
        deaths++;
    }

    public void addBlockMined() {
        blockedMined++;
    }

    public void startRecordingTimeLived() {
        previous = System.currentTimeMillis();
    }

    public void stopRecordingTimeLived() {
        timeLived += System.currentTimeMillis() - previous;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getBlockedMined() {
        return blockedMined;
    }

    public long getTimeLived() {
        return timeLived;
    }

    public long getTimeLivedMinutes() {
        long seconds = timeLived / 1000;
        return seconds / 60;
    }

}
