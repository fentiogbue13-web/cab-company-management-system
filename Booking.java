public record Booking(String vehicleID, String pickupLocation, String destination) {
    @Override
    public String toString() {
        return "Vehicle: " + vehicleID + " from " + pickupLocation + " to " + destination;
    }
}
