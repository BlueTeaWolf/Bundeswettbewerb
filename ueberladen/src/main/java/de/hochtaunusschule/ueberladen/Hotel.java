package de.hochtaunusschule.ueberladen;

public class Hotel {
    private final double score;
    private final int totalDuration;

    public Hotel(double score, int totalDuration) {
        this.score = score;
        this.totalDuration = totalDuration;
    }

    public double score() {
        return score;
    }

    @Override
    public String toString() {
        return totalDuration + "(" + score + ")";
    }

    public int totalDuration() {
        return totalDuration;
    }

    public boolean isEnd() {
        return score == 10_000;
    }
}
