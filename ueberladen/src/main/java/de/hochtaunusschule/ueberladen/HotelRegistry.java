package de.hochtaunusschule.ueberladen;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class HotelRegistry {
    private static final int MAX_STEP = (int) TimeUnit.HOURS.toMinutes(6);
    private final NavigableMap<Integer, Hotel> hotels = new TreeMap<>();

    public void register(Hotel hotel) {
        hotels.put(hotel.totalDuration(), hotel);
    }

    public Collection<Hotel> nextCandidates(Hotel hotel) {
        int start = hotel.totalDuration();
        return hotels.subMap(start, false,
                start + MAX_STEP, true).values();
    }
}
