package de.hochtaunusschule.a3;

/**
 * @author David (_Esel)
 */
public class Player {
    private final Dice dice;
    private final Field target;
    private final Field start;

    private final boolean[] targets = new boolean[4];
    private int startFigures = 4;

    public Player(Game game, Dice dice, int targetField, int startField) {
        this.dice = dice;
        target = game.field(targetField);
        start = game.field(startField);
    }

    public Field getTarget() {
        return target;
    }

    public Field getStart() {
        return start;
    }

    public Dice getDice() {
        return dice;
    }

    public boolean hasFiguresInStart() {
        return startFigures > 0;
    }

    public void takeFigureFromStart() {
        if (!hasFiguresInStart()) {
            throw new IllegalStateException();
        }
        startFigures--;
    }

    public void teleportToStart() {
        startFigures++;
    }

    public boolean canFinish(int position) {
        return targets.length > position - 1 && !targets[position-1];
    }

    public void finish(int position) {
        targets[position - 1] = true;
    }
}
