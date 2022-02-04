package de.hochtaunusschule.ueberladen;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Hotel> route = new ArrayList<>();
    private double score;
    public final int length, steps, maxLength;

    public Path(int length, int steps, int maxLength) {
        this.length = length;
        this.maxLength = maxLength;
        this.steps = steps;
    }

    public String format() {
        StringBuilder builder = new StringBuilder();
        builder.append(totalScore());
        builder.append(": ");
        builder.append(route.get(0));
        for (int i = 1; i < route.size(); i++) {
            builder.append(" -> ").append(route.get(i));
        }
        return builder.toString();
    }

    public void add(Hotel hotel) {
        if (hotel == null || route.contains(hotel)) return;
        if (isEmpty() || hotel.score() < score) {
            score = hotel.score();
        }
        route.add(hotel);
    }

    public void removeLast() {
        if (route.isEmpty()) return;
        Hotel h = route.remove(route.size() - 1);
        if (h.score() == score && !isEmpty()) {
            score = route.stream().mapToDouble(Hotel::score).min().getAsDouble();
        }
    }

    public int lastPosition() {
        return route.isEmpty() ? 0 : route.get(route.size() - 1).totalDuration();
    }

    public int kuerzesteEtappe() {
        int distanceLeft = length - lastPosition();
        int shortest = distanceLeft % maxLength;
        return shortest > 0 ? shortest : maxLength;
    }

    public Path copy() {
        Path c = new Path(length, steps, maxLength);
        for (Hotel h : route) {
            c.add(h);
        }
        return c;
    }

    public boolean isEmpty() {
        return route.isEmpty();
    }

    public double totalScore() {
        return score;
    }
}
