
public class Cab extends Vehicle {
    private String driverName;
    private int pricePerJourney;

    public Cab(String id, String driverName, int pricePerJourney) {
        super(id);
        this.driverName = driverName;
        this.pricePerJourney = pricePerJourney;
    }

    @Override
    public int getTakings() {
        return getBookings().size() * pricePerJourney;
    }

    @Override
    public String toString() {
        return "Cab " + getId() + " driven by " + driverName;
    }
}
