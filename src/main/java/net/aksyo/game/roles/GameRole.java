package net.aksyo.game.roles;

import net.aksyo.player.AcePlayer;

import java.util.function.Consumer;

public abstract class GameRole {


    public abstract String getName();
    public abstract String getGameName();

    public abstract String getDescription();
    public abstract String[] getInformation();

    public abstract Consumer<AcePlayer> applyPowers();

    public abstract boolean isJoker();

}
