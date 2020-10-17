package net.aksyo.game.roles;

import net.aksyo.game.roles.gamesroles.As;
import net.aksyo.game.roles.gamesroles.Joker;
import net.aksyo.game.roles.gamesroles.Pion;

import java.util.function.Supplier;

public enum RoleType {

    JOKER(Joker::new),
    AS(As::new),
    PION(Pion::new);

    private final Supplier<GameRole> factory;

    RoleType(Supplier<GameRole> factory) {
        this.factory = factory;
    }

    public GameRole get() {
        return factory.get();
    }

}
