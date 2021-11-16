package de.hochtaunusschule.schiebeparkplatz;

public class Main {
    public static void main(String[] args) {
        SchiebeParkpPlatz schiebeParkpPlatz = new SchiebeParkpPlatz();
        for (ParkSlot slot : schiebeParkpPlatz.slots()) {
            System.out.println(slot.getIdentifier() + ": "
                    + slot.bestPath().stringRepresentation());
        }
    }
}

