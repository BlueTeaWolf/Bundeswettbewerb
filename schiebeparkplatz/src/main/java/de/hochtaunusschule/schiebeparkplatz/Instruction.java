package de.hochtaunusschule.schiebeparkplatz;

public class Instruction {
    private final BlockingCar car;
    private final Direction direction;
    private final int amount;

    public Instruction(BlockingCar car, Direction direction, int amount) {
        this.car = car;
        this.direction = direction;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return car.getIdentifier() + " " + amount + direction.displayName();
    }
}
