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
    private UserRegistration userRegistration;
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList;

    public BikeRental() {
        userRegistration = new UserRegistration();
        activeRentalsList = new LinkedList<>();
        locationValid = false;
    }

    public void simulateApplicationInput() {
        System.out.println("This is the simulation of the e-bike rental process.");

        isRegisteredUser = true;
        emailAddress = "test@example.com";
        location = "Campus Gate 5";

        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);

        if (!locationValid) {
            System.out.println("Location is invalid, exiting process.");
            return;
        }

        System.out.println("Simulating e-bike reservation...");
        reserveBike(bikeID);

        System.out.println("Displaying the active rentals...");
        viewActiveRentals();

        System.out.println("Simulating the end of the trip...");
        removeTrip(bikeID);

        System.out.println("Displaying the active rentals after trip end...");
        viewActiveRentals();

        System.out.println("Rental simulation completed.");
    }

    private String analyseRequest(boolean isRegisteredUser, String emailAddress, String location) {
        if (isRegisteredUser) {
            System.out.println("Welcome back, " + emailAddress + "!");
        } else {
            System.out.println("You're not our registered user. Please consider registering.");
            userRegistration.registration();
        }

        return validateLocation(location);
    }

    private String validateLocation(String location) {
        locationValid = false;
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                locationValid = true;
                return bike.getBikeID();
            }
        }

        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }

    private void reserveBike(String bikeID) {
        if (bikeID == null) {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
            return;
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                tripStartTime = LocalDateTime.now();
                bike.setAvailable(false);
                bike.setLastUsedTime(tripStartTime);
                System.out.println("Reserving the bike with the " + bikeID + ". Please following the on-screen instructions to locate the bike and start your pleasant journey.");

                activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                activeRentalsList.add(activeRental);
                break;
            }
        }
    }

    private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }

        for (ActiveRental rental : activeRentalsList) {
            System.out.println(rental);
        }
    }

    private void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeID)) {
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

        System.out.println("Your trip has ended. Thank you for riding with us.");
    }
}