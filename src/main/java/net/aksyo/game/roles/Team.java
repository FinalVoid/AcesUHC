package net.aksyo.game.roles;

import java.util.Map;

public interface Team {

    public String getName();

    public String getGameName();

    public String getDescription();

    public GameRole[] getRoles();

    public Map<SubRole, Integer> getSubRoles();

}
