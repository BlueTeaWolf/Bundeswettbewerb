package de.hochtaunusschule.schiebeparkplatz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Path implements Comparable<Path> {
    private final List<Instruction> instructions = new ArrayList<>();
    private boolean invalid;

    public void logMove(BlockingCar car, Direction direction,
                        int from, int to) {
        int distance = Math.abs(from - to);
        instructions.add(new Instruction(car, direction, distance));
    }

    public String stringRepresentation() {
        return instructions.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
    }

    public int score() {
        int base = instructions.size() * 100;
        int totalAmount = instructions.stream()
                .mapToInt(Instruction::getAmount)
                .sum();
        return base + totalAmount;
    }

    public void invalidate() {
        invalid = true;
    }

    public boolean isValid() {
        return !invalid;
    }

    @Override
    public int compareTo(Path o) {
        return Integer.compare(score(), o.score());
    }
}
