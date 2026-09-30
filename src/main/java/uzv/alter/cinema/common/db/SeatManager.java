package uzv.alter.cinema.common.db;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SeatManager {

    private static final String FILE_PATH = "booked_seats.txt";
    private final Map<String, Set<String>> bookedSeatsMap = new HashMap<>();

    public SeatManager() {
        loadBookedSeats();
    }

    public boolean isBooked(String sessionId, String seatKey) {
        Set<String> seats = bookedSeatsMap.get(sessionId);
        return seats != null && seats.contains(seatKey);
    }

    public void bookSeat(String sessionId, String seatKey) {
        bookedSeatsMap.computeIfAbsent(sessionId, k -> new HashSet<>()).add(seatKey);
    }

    public synchronized void loadBookedSeats() {
        bookedSeatsMap.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || !line.contains(":")) continue;

                String[] parts = line.split(";", 2);
                String sessionId = parts[0].trim();
                String seatKey = parts[1].trim();

                bookedSeatsMap.computeIfAbsent(sessionId, k -> new HashSet<>()).add(seatKey);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void saveBookedSeats() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, Set<String>> entry : bookedSeatsMap.entrySet()) {
                String sessionId = entry.getKey();
                for (String seatKey : entry.getValue()) {
                    writer.write(sessionId + ";" + seatKey);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}