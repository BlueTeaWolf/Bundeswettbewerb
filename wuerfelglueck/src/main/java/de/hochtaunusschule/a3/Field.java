package de.hochtaunusschule.a3;

/**
 * @author David (_Esel)
 */
public class Field {
    private final Game game;
    private final int index;
    private Player player;

    public Field(Game game, int index) {
        this.game = game;
        this.index = index;
    }

    public boolean notAcquiredBy(Player player) {
        return this.player != player;
    }

    public boolean acquiredBy(Player player) {
        return this.player == player;
    }

    public boolean isEmpty() {
        return player == null;
    }

    public int getIndex() {
        return index;
    }

    public Field next(int move) {
        return game.field(index + move);
    }

    public Player getPlayer() {
        return player;
    }

    public void aquire(Player player) {
        if (!isEmpty() && notAcquiredBy(player)) {
            this.player.teleportToStart();
        }
        this.player = player;
    }

    public void clear() {
        player = null;
    }
}
