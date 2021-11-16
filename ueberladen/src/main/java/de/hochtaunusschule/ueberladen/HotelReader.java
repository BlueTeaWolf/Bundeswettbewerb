package de.hochtaunusschule.ueberladen;

import java.io.*;

public class HotelReader implements AutoCloseable {
    private final BufferedReader reader;

    public HotelReader(File file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
    }

    private int readInt() throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private Hotel readHotel() throws IOException {
        String[] line = reader.readLine().split(" ");
        return new Hotel(Double.parseDouble(line[1]), Integer.parseInt(line[0]));
    }

    public HotelRegistry readAll() throws IOException {
        HotelRegistry registry = new HotelRegistry();
        int size = readInt();
        int end = readInt();
        registry.register(new Hotel(10_000, end));
        for (int i = 0; i < size; i++) {
            registry.register(readHotel());
        }
        return registry;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
