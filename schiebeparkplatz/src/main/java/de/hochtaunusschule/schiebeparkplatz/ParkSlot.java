package de.hochtaunusschule.schiebeparkplatz;

import java.util.*;

public class ParkSlot extends AbstractSlot {
    private final int index;
    private final ParkSlot previous;
    private ParkSlot next;
    private BlockBinding locked;

    public ParkSlot(int index, char identifier, ParkSlot previous) {
        super(identifier);
        this.index = index;
        this.previous = previous;
        if (previous != null) {
            previous.next = this;
        }
    }

    public int getIndex() {
        return index;
    }

    public char getIdentifier() {
        return Character.toUpperCase(identifier);
    }

    public ParkSlot neighbor(Direction direction) {
        switch (direction) {
            case LEFT: return previous;
            case RIGHT: return next;
            default:
                throw new IllegalStateException();
        }
    }

    public boolean free() {
        return locked == null;
    }

    public Path bestPath() {
        return findPaths().stream()
                .filter(Path::isValid)
                .min(Comparator.naturalOrder())
                .orElseThrow(IllegalStateException::new);
    }

    public Collection<Path> findPaths() {
        return Arrays.asList(calculatePath(Direction.LEFT),
                calculatePath(Direction.RIGHT));
    }

    private Path calculatePath(Direction direction) {
        Path path = new Path();
        if (!free()
            && locked.getBlock().move(this, direction, path)) {
            path.invalidate();
        }
        return path;
    }

    public BlockingCar blockedBy() {
        return locked == null ? null : locked.getBlock();
    }

    public BlockingCar blockedBy(Direction offset) {
        ParkSlot neighbor = neighbor(offset);
        if (neighbor == null || neighbor.free()) {
            return null;
        }
        return neighbor.locked.getBlock();
    }

    public void lock(BlockBinding blockingCar) {
        locked = blockingCar;
        blockingCar.setSlot(this);
    }
}
