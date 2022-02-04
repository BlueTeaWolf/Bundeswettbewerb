package de.hochtaunusschule.schiebeparkplatz;

public class BlockingCar extends AbstractSlot {
    private final SchiebeParkpPlatz parkpPlatz;
    private final BlockBinding[] space = new BlockBinding[2];

    public BlockingCar(SchiebeParkpPlatz parkpPlatz, char identifier) {
        super(identifier);
        this.parkpPlatz = parkpPlatz;
    }

    private BlockBinding binding(Direction direction) {
        switch (direction) {
            case LEFT: return space[0];
            case RIGHT: return space[space.length - 1];
            default:
                throw new IllegalStateException();
        }
    }

    private Direction bindTypeBySlot(ParkSlot slot) {
        int i = 0;
        for (BlockBinding binding : space) {
            if (binding.getSlot() == slot) {
                return Direction.values()[i];
            }
            i++;
        }
        throw new IllegalStateException(slot.identifier + " not in space in " + slot);
    }

    public boolean move(ParkSlot free, Direction direction, Path instructions) {
        ParkSlot slot = free.neighbor(direction);
        if (slot == null) {
            return true;
        }
        ParkSlot required = slot.neighbor(direction);
        if (required == null) {
            return true;
        }
        if (!required.free()) {
            if (required.blockedBy().move(required, direction, instructions)) {
                return true;
            }
        }
        instructions.logMove(this, direction,
                binding(direction).getSlot().getIndex(),
                required.getIndex());
        return false;
    }

    public void atIndex(int index) {
        for (int i = 0; i < space.length; i++) {
            BlockBinding binding = new BlockBinding(this);
            parkpPlatz.at(index + i).lock(binding);
            space[i] = binding;
        }
    }
}
