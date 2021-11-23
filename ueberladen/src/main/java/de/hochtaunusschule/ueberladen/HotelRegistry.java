package de.hochtaunusschule.ueberladen;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class HotelRegistry {
    public static final int MAX_STEP = (int) TimeUnit.HOURS.toMinutes(6);
    private final NavigableMap<Integer, Hotel> hotels = new TreeMap<>();
    private int end;

    public void setEnd(int end) {
        this.end = end;
    }

    public void register(Hotel hotel) {
        hotels.put(hotel.totalDuration(), hotel);
    }

    public int getEnd() {
        return end;
    }

    public Collection<Hotel> nextCandidates(Path path) {
        int start = path.lastPosition();
        return hotels.subMap(start + path.kuerzesteEtappe(), true, start + MAX_STEP, true).values();
    }
}
