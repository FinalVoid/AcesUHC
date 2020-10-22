package net.aksyo.game.roles.gamesroles.subroles;

import net.aksyo.game.roles.SubRole;

import java.util.function.Supplier;

public enum SubRoleType {

    NULL(null),
    PIONFOURBE(PionFourbe::new);

    private Supplier<SubRole> subRole;

    SubRoleType(Supplier<SubRole> subRole) {
        this.subRole = subRole;
    }

    public SubRole get() {
        return subRole.get();
    }
}
