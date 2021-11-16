package de.hochtaunusschule.ueberladen;

import java.util.Comparator;
import java.util.List;

public class Path {
    private final List<Hotel> route;

    public Path(List<Hotel> route) {
        this.route = route;
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

    public double totalScore() {
        return route.stream()
                .min(Comparator.comparingDouble(Hotel::score))
                .map(Hotel::score)
                .orElseThrow(() -> new IllegalStateException("Empty route " + route));
    }
}
