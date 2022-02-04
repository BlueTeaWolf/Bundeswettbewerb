package de.hochtaunusschule.marktwaage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WaageIndex {
    private final int[] wights;
    private final List<Integer> wightsSet;
    private final List<Waage> waages = new ArrayList<>();

    public WaageIndex(List<Integer> wightsSet) {
        this.wightsSet = wightsSet;
        System.out.println(wightsSet);
        wights = wightsSet.stream().mapToInt(Integer::intValue).toArray();
    }

    public Waage optimal(int wight) {
        int bestScore = Integer.MAX_VALUE;
        Waage best = null;
        for (Waage waage : waages) {
            int score = waage.balance(wight);
            if (score < bestScore) {
                bestScore = score;
                best = waage;
            }
        }
        return best;
    }

    public void generate() {
        int[][] combinations = new int[(int) Math.pow(2, wights.length)][];
        combinations[0] = new int[0];
        int size = 1;
        for (int wight : wights) {
            int lSize = size;
            for (int i = 0; i < lSize; i++) {
                int[] left = IntArrays.append(combinations[i], wight);
                //System.out.println(Arrays.toString(left));
                combinations[size++] = left;
            }
        }
    }
}
