package de.hochtaunusschule.zzz;

import java.util.Arrays;

public class Model {
    private final int totalKeys;
    private final int originalKeys;
    private final long[] keys;

    public Model(int totalKeys, int originalKeys, long[] keys) {
        this.totalKeys = totalKeys;
        this.originalKeys = originalKeys;
        this.keys = keys;
    }

    public long[] getKeys() {
        return keys;
    }

    public int getOriginalKeys() {
        return originalKeys;
    }

    @Override
    public String toString() {
        return "Model{" +
                "totalKeys=" + totalKeys +
                ", originalKeys=" + originalKeys +
                ", keys=" + Arrays.toString(keys) +
                '}';
    }
}
