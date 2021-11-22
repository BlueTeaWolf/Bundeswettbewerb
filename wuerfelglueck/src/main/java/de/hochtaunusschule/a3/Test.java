package de.hochtaunusschule.a3;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author David (_Esel)
 */
public class Test {
    public static void main(String[] args) {
        Stream.of("abv", "aaaaaaa", "a").sorted(Comparator.comparingInt(String::length).reversed()).forEach(s -> {
            System.out.println(s);
        });
    }
}
