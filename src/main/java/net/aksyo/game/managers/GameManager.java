package net.aksyo.game.managers;

import net.aksyo.game.GameOption;
import net.aksyo.game.GameState;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class GameManager {

    private GameState currentGameState = GameState.WAITING;
    private GameState previousGameState = null;
    private boolean damage = false;
    private boolean pvp = false;
    private boolean debug = false;
    private boolean movement = true;
    private boolean jokerPacte = false;
    private LinkedList<Player> immunePlayers = new LinkedList<>();


    public GameManager() {

    }

    /**
     *
     * @param gameState The gamestate you wish to check
     * @return If it is the current gamestate
     */
    public boolean isGameState(GameState gameState) {
        return currentGameState == gameState;
    }

    /**
     *
     * @param gameState Sets this gamestate to the current gamestate
     */
    public void setGameState(GameState gameState) {
        this.previousGameState = this.currentGameState;
        this.currentGameState = gameState;
    }

    /**
     *
     * @return The current game state
     */
    public GameState getCurrentGameState() {
        return currentGameState;
    }

    /**
     * Freezes the current game
     * @param optionalFreeze if the freeze is optional or was forced
     */
    public void freezeGame(boolean optionalFreeze) {
        this.previousGameState = this.currentGameState;
        currentGameState = GameState.FROZEN;

    }

    /**
     * Unfreezes the game, restores the gamestate
     */
    public void unfreezeGame() {
        this.currentGameState = this.previousGameState;
    }

    /**
     *
     * @return if the game is in debug mode;
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * activates debug mode
     */
    public void activateDebug() {
        debug = true;
    }

    /**
     * deactivates debug mode
     */
    public void deactivateDebug() { debug = false; }

    /**
     *
     * @return If damages are activated
     */
    public boolean isDamage() {
        return damage;
    }

    /**
     *
     * @param damage set if damage is activated
     */
    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    /**
     *
     * @return if players are allowed to move or not
     */
    public boolean isMovement() {
        return movement;
    }

    /**
     *
     * @param movement if players can move or not (new value)
     */
    public void setMovement(boolean movement) {
        this.movement = movement;
    }

    /**
     *
     * @return if pvp is activated
     */
    public boolean isPvp() { return pvp; }

    /**
     *
     * @param pvp set if pvp is activated
     */
    public void setPvp(boolean pvp) { this.pvp = pvp; }

    /**
     *
     * @param jokerPacte set if the pacte is available or done
     */
    public void setJokerPacte(boolean jokerPacte) {
        this.jokerPacte = jokerPacte;
    }

    /**
     *
     * @return if the pion can do the pacte with the joker
     */
    public boolean isJokerPacte() {
        return jokerPacte;
    }

    /**
     *
     * @return The list of immuned players
     */
    public LinkedList<Player> getImmunePlayers() {
        return immunePlayers;
    }
}
