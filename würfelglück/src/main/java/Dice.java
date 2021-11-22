import java.util.Random;

public class Dice implements Comparable<Dice> {
    private final int[] sides;

    public Dice(int[] sides) {
        this.sides = sides;
    }

    public int roll(Random random) {
        return sides[random.nextInt(sides.length)];
    }

    @Override
    public int compareTo(Dice o) {
        return 0;
    }
}
