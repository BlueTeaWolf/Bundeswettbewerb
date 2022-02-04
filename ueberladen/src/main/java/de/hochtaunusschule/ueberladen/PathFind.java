package de.hochtaunusschule.ueberladen;

public class PathFind {
    private static final int MAX_STEPS = 5;
    private final HotelRegistry registry;
    private Path best;

    public PathFind(HotelRegistry registry) {
        this.registry = registry;
    }

    public Path bestPath() {
        best = init();
        collectPaths(init());
        return best;
    }

    private Path init() {
        return new Path(registry.getEnd(), MAX_STEPS, HotelRegistry.MAX_STEP);
    }

    private void collectPaths(Path route) {
        int min = route.lastPosition() + route.kuerzesteEtappe();
        int max = route.lastPosition() + HotelRegistry.MAX_STEP;
        if (min <= route.length && route.length <= max) {
            if ( route.totalScore() > best.totalScore()) {
                best = route.copy();
            }
        } else {
            for (Hotel hotel : registry.nextCandidates(route)) {
                if (best.isEmpty() || hotel.score() > best.totalScore()) {
                    route.add(hotel);
                    collectPaths(route);
                    route.removeLast();
                }
            }
        }
    }
}
