import java.util.Arrays;
import java.util.List;


public class Bus extends Vehicle {
    private int pricePerStop;
    private String[] route;

    public Bus(String id, int pricePerStop, String[] route) {
        super(id);
        this.pricePerStop = pricePerStop;
        this.route = route;
    }

    @Override
    public int getTakings() {
        int total = 0;
        List<String> routeList = Arrays.asList(route);

        for (Booking b : getBookings()) {
            int start = routeList.indexOf(b.pickupLocation());
            int end = routeList.indexOf(b.destination());

            if (start != -1 && end != -1) {
                int stops;
                if (end >= start) {
                    stops = end - start;
                } else {
                    stops = (route.length - start) + end;
                }
                total += stops * pricePerStop;
            }
        }
        return total;
    }

    @Override
    public String toString() {
        String routeStr = String.join(", ", route);
        return "Bus " + getId() + " has the route " + routeStr;
    }
}
