package de.hochtaunusschule.a3;

import java.util.Arrays;
import java.util.Random;

public class Dice implements Comparable<Dice> {
    private static final int GAMES = 50;

    private final int[] sides;

    public Dice(int[] sides) {
        this.sides = sides;
    }

    @Override
    public String toString() {
        return "Dice{" +
            "sides=" + Arrays.toString(sides) +
            '}';
    }

    public int roll(Random random) {
        return sides[random.nextInt(sides.length)];
    }

    @Override
    public int compareTo(Dice o) {
        Random random = new Random();
        int compare = 0;
        for (int i = 0; i < GAMES; i++) {
            Game game = new Game(random, this, o);
            if (game.play() == this) {
                compare++;
            } else {
                compare--;
            }
        }
        for (int i = 0; i < GAMES; i++) {
            Game game = new Game(random, o, this);
            if (game.play() == this) {
                compare++;
            } else {
                compare--;
            }
        }
        return compare;
    }
}
