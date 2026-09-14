import java.util.ArrayList;
import java.util.List;


public abstract class Vehicle {
    private String id;
    private List<Booking> bookings;

    public Vehicle(String id) {
        this.id = id;
        this.bookings = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public abstract int getTakings();
}
