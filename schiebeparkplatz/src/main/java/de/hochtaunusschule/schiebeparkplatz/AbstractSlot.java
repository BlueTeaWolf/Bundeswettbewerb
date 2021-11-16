package de.hochtaunusschule.schiebeparkplatz;

public class AbstractSlot {
    protected final char identifier;

    public AbstractSlot(char identifier) {
        this.identifier = identifier;
    }

    public char getIdentifier() {
        return Character.toUpperCase(identifier);
    }
}
