import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private LinkedList<ActiveRental> activeRentalsList;

    public BikeRental() {
        activeRentalsList = new LinkedList<>();
        locationValid = false;
    }

    public void simulateApplicationInput() {
        System.out.println("This is the simulation of the e-bike rental process.");

        isRegisteredUser = true;
        emailAddress = "user@example.com";
        location = "Nanjing South";

        System.out.println("\nAnalyzing rental request...");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);

        if (!locationValid) {
            System.out.println("Location invalid, process stopped.");
            return;
        }

        System.out.println("\nReserving bike...");
        reserveBike(bikeID);

        System.out.println("\nCurrent active rentals:");
        viewActiveRentals();

        System.out.println("\nEnding trip...");
        removeTrip(bikeID);

        System.out.println("\nActive rentals after trip ended:");
        viewActiveRentals();

        System.out.println("\nRental simulation completed.");
    }

    public String analyseRequest(boolean registered, String email, String loc) {
        this.isRegisteredUser = registered;
        this.emailAddress = email;
        this.location = loc;

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equalsIgnoreCase(loc) && bike.isAvailable()) {
                locationValid = true;
                return bike.getBikeID();
            }
        }
        locationValid = false;
        return null;
    }

    public void reserveBike(String bikeID) {
        if (bikeID == null) return;

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(false);
                break;
            }
        }

        ActiveRental rental = new ActiveRental(bikeID, emailAddress, LocalDateTime.now());
        activeRentalsList.add(rental);
        System.out.println("Bike " + bikeID + " reserved successfully.");
    }

    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals.");
            return;
        }
        for (ActiveRental ar : activeRentalsList) {
            System.out.println(ar);
        }
    }

    public void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental ar = iterator.next();
            if (ar.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                break;
            }
        }
        System.out.println("Bike " + bikeID + " returned.");
    }
}