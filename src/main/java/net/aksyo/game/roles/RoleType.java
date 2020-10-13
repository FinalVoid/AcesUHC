package net.aksyo.game.roles;

import net.aksyo.game.roles.gamesroles.Joker;

import javax.management.relation.Role;
import java.util.function.Supplier;

public enum RoleType {

    JOKER(Joker::new);

    private final Supplier<GameRole> factory;

    RoleType(Supplier<GameRole> factory) {
        this.factory = factory;
    }

    public GameRole get() {
        return factory.get();
    }

}
