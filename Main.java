import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String vehiclesFilename = "vehicles.csv";
        String bookingsFilename = "bookings.csv";
        String reportFilename = "report.txt";

        System.out.println("Using files: " + vehiclesFilename + ", " + bookingsFilename + " and " + reportFilename);

        ArrayList<Vehicle> theVehicles = readVehicleDetails(vehiclesFilename);
        CabCompany cabCompany = new CabCompany(theVehicles);

        cabCompany.readBookings(bookingsFilename);
        cabCompany.writeReport(reportFilename);

        System.out.println("\n--- Vehicle List ---");
        cabCompany.listAllVehicles();

        System.out.println("\n--- Bookings List ---");
        cabCompany.listAllBookings();
    }

    public static Vehicle decodeVehicleDetails(String line) {
        String[] parts = line.split(",");
        String type = parts[0];
        String id = parts[1];

        if (type.equals("C")) {
            return new Cab(id, parts[2], Integer.parseInt(parts[3]));
        } else if (type.equals("B")) {
            int price = Integer.parseInt(parts[2]);
            String[] route = parts[3].split(":");
            return new Bus(id, price, route);
        }
        return null;
    }

    public static ArrayList<Vehicle> readVehicleDetails(String filename) {
        ArrayList<Vehicle> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                Vehicle v = decodeVehicleDetails(scanner.nextLine());
                if (v != null) {
                    list.add(v);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Vehicle file not found.");
        }
        return list;
    }
}
