package de.hochtaunusschule.marktwaage;

import java.util.Arrays;

public class IntArrays {
    public static int[] append(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    public static int sum(int[] arr) {
        int total = 0;
        for (int element : arr) {
            total += element;
        }
        return total;
    }
}
