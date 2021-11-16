package de.hochtaunusschule.schiebeparkplatz;

public class SchiebeParkpPlatz {
    private static char[] IDENTIFIERS = "abcdefg".toCharArray();
    private ParkSlot[] state = new ParkSlot[7];

    {
        ParkSlot previous = null;
        for (int i = 0; i < IDENTIFIERS.length; i++) {
            previous = new ParkSlot(i, IDENTIFIERS[i], previous);
            state[i] = previous;
        }
        new BlockingCar(this, 'h').atIndex(2);
        new BlockingCar(this, 'i').atIndex(5);
    }

    public ParkSlot[] slots() {
        return state;
    }

    public ParkSlot at(int index) {
        return state[index];
    }
}
