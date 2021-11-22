package de.hochtaunusschule.marktwaage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Waage {
    private final int[] left;
    private final int leftSum;
    private final int rightSum;

    public Waage(int[] left, int totalSum) {
        this.left = left;
        this.leftSum = IntArrays.sum(left);
        rightSum = totalSum - leftSum;
    }

    private static String formatSide(Stream<Integer> side) {
        return side
            .map(Object::toString)
            .collect(Collectors.joining(" + "));
    }

    public String stringRepresentation(int add) {
        return "";
        //return formatSide(Arr)
        //    + " (+ " + add + ") <-> "
        //    + formatSide(right);
    }

    public int balance(int add) {
        return Math.abs(leftSum + add - rightSum);
    }
}
