package net.aksyo.game;

public enum GameState {

    WAITING("Waiting"),
    GAME("Game"),
    ENDING("End"),
    CLOSED("Close"),
    FROZEN("Freezed");

    private String name;

    GameState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
