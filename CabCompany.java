import java.util.*;
import java.io.*;

public class CabCompany {
    private Map<String, Vehicle> vehicleMap;

    public CabCompany(ArrayList<Vehicle> vehicles) {
        vehicleMap = new HashMap<>();
        for (Vehicle v : vehicles) {
            vehicleMap.put(v.getId(), v);
        }
    }

    public void readBookings(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Booking b = new Booking(parts[0], parts[1], parts[2]);
                    Vehicle v = vehicleMap.get(b.vehicleID());
                    if (v != null) {
                        v.addBooking(b);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Booking file not found.");
        }
    }

    public void writeReport(String filename) {
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            pw.println("Cab Company Report");
            pw.println();

            int total = 0;
            for (Vehicle v : vehicleMap.values()) {
                int takings = v.getTakings();
                total += takings;
                pw.println(v.toString() + " had " + v.getBookings().size() + " journeys and made £" + takings);
            }
            pw.println("\nTotal takings for the day £" + total);
            pw.println("=".repeat(43));
        } catch (Exception e) {
            System.out.println("Error writing report.");
        }
    }

    public void listAllVehicles() {
        for (Vehicle v : vehicleMap.values()) {
            System.out.println(v);
        }
    }

    public void listAllBookings() {
        for (Vehicle v : vehicleMap.values()) {
            for (Booking b : v.getBookings()) {
                System.out.println(b);
            }
        }
    }

    public int getTakingsForVehicle(String id) {
        Vehicle v = vehicleMap.get(id);
        return (v != null) ? v.getTakings() : 0;
    }
}
