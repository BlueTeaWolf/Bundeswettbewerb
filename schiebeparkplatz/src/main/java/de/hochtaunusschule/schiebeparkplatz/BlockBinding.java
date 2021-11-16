package de.hochtaunusschule.schiebeparkplatz;

public class BlockBinding {
    private final BlockingCar block;
    private ParkSlot slot;

    public BlockBinding(BlockingCar block) {
        this.block = block;
    }

    public void setSlot(ParkSlot slot) {
        this.slot = slot;
    }

    public BlockingCar getBlock() {
        return block;
    }

    public ParkSlot getSlot() {
        return slot;
    }
}
