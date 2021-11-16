package de.hochtaunusschule.schiebeparkplatz;

public enum Direction {
    RIGHT("rechts"),
    LEFT("links", RIGHT);

    private final String displayName;
    private Direction opposite;

    Direction(String displayName, Direction opposite) {
        this(displayName);
        this.opposite = opposite;
        opposite.opposite = this;
    }

    Direction(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    public Direction opposite() {
        return opposite;
    }
}
