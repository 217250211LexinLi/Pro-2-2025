import java.util.List;

public class BikeRental {
    private List<Bike> bikes;

    public BikeRental() {
        bikes = List.of(
            new Bike("B001", true, 100, null, "Station A"),
            new Bike("B002", false, 80, null, "Station B"),
            new Bike("B003", true, 90, null, "Station A")
        );
    }

    public String processRequest(boolean registered, String email, String location) {
        if (!registered) {
            System.out.println("You are not registered.");
            return null;
        }
        return findAvailableBike(location);
    }

    private String findAvailableBike(String location) {
        for (Bike bike : bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                bike.setAvailable(false);
                return bike.getBikeId();
            }
        }
        System.out.println("No bikes available at this location.");
        return null;
    }
}