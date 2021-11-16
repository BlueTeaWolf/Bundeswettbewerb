package de.hochtaunusschule.ueberladen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class PathFind {
    private final HotelRegistry registry;
    private final List<Path> paths = new ArrayList<>();

    public PathFind(HotelRegistry registry) {
        this.registry = registry;
    }

    public Path bestPath() {
        collectPaths(new ArrayList<>(), new Hotel(1_000, 0));
        return paths.stream()
                .max(Comparator.comparingDouble(Path::totalScore))
                .orElseThrow(() -> new IllegalStateException("No paths found"));
    }

    private void collectPaths(List<Hotel> before, Hotel hotel) {
        Collection<Hotel> candidates = registry.nextCandidates(hotel);
        for (Hotel candidate : candidates) {
            if (!before.contains(candidate)) {
                List<Hotel> clone = new ArrayList<>(before);
                if (candidate.isEnd()) {
                    paths.add(new Path(before));
                } else {
                    clone.add(candidate);
                    if (clone.size() <= 5) {
                        collectPaths(clone, candidate);
                    }
                }
            }
        }
    }
}
