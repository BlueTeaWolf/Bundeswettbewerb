package de.hochtaunusschule.schiebeparkplatz;

public class SchiebeParkpPlatz {
    private final ParkSlot[] state;

    public SchiebeParkpPlatz(char start, char end) {
        state = new ParkSlot[end - start + 1];
        ParkSlot previous = null;
        int i = 0;
        for (char id = start; id <= end; id++) {
            previous = new ParkSlot(i, id, previous);
            state[i++] = previous;
        }
    }

    public ParkSlot[] slots() {
        return state;
    }

    public ParkSlot at(int index) {
        return state[index];
    }
}
