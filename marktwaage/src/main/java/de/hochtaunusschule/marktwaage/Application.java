package de.hochtaunusschule.marktwaage;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Application {
    public static void main(String[] args) {

    }

    private List<Waage> waages;

    private void collect(List<Integer> current, Queue<Integer> left) {

    }

    private void combinationForSize(List<int[]> combinations,
                                    int[] array, int[] wigths,
                                    int start, int end,
                                    int index, int r) {
        if (index == r) {
            combinations.add(array.clone());
        }
    }

    public void generate(int... wights) {
        Set<Integer> all = new HashSet<>();
        for (int i = 0; i < wights.length; i++) {
            Set<Integer> clone = new HashSet<>(all);
            wights[0] + wights[1] + wights[2]

        }
    }
}
