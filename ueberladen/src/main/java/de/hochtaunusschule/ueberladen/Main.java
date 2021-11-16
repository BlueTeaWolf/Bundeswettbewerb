package de.hochtaunusschule.ueberladen;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        for (File file : new File("ueberladen/src/main/resources/hotell/").listFiles()) {
            System.out.println("-> " + file.getName());
            try (HotelReader reader = new HotelReader(file)) {
                PathFind pathFind = new PathFind(reader.readAll());
                System.out.println("   " + pathFind.bestPath().format());
            }
        }
    }
}
