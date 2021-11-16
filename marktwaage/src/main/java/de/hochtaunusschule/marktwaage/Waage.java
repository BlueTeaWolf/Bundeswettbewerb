package de.hochtaunusschule.marktwaage;

public class Waage {
    private final int left;
    private final int right;

    public Waage(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int balance(int add) {
        return Math.abs(left + add - right);
    }
}
