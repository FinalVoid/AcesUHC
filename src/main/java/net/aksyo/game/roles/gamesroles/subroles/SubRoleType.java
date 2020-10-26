package net.aksyo.game.roles.gamesroles.subroles;

import net.aksyo.game.roles.SubRole;

import java.util.function.Supplier;

public enum SubRoleType {

    NULL(null),
    PIONFOURBE(PionFourbe::new);

    private Supplier<SubRole> factory;

    SubRoleType(Supplier<SubRole> factory) {
        this.factory = factory;
    }

    public SubRole get() {
        return factory.get();
    }
}
