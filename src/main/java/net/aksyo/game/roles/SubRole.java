package net.aksyo.game.roles;

import net.aksyo.player.AcePlayer;

public abstract class SubRole {


    public abstract String getName();
    public abstract String getGameName();

    public abstract String getDescription();
    public abstract String[] getInformation();

    public abstract RoleType getParentRole();

    public abstract void revealAction(AcePlayer player);

}
