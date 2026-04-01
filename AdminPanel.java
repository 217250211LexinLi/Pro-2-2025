import java.time.LocalDateTime;
import java.util.Scanner;

public class AdminPanel {
    private final UserService userService;
    private final BikeService bikeService;
    private final RentalService rentalService;
    private final Scanner scanner;

    public AdminPanel() {
        userService = new UserService();
        bikeService = new BikeService();
        rentalService = new RentalService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n===== Bike Rental System =====");
            System.out.println("1. Demo the Bike Rental System");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int c = scanner.nextInt();
            if (c == 1) demo();
            else if (c == 2) break;
        }
    }

    private void demo() {
        System.out.println("\nStarting demo...");
        String email = "test@example.com";
        String location = "Campus Gate 5";

        if (!userService.isRegistered(email)) {
            userService.register();
        }

        if (!bikeService.isLocationValid(location)) {
            System.out.println("No bikes available.");
            return;
        }

        String bikeID = bikeService.findAvailableBike(location);
        LocalDateTime start = LocalDateTime.now();

        bikeService.reserveBike(bikeID);
        rentalService.startRental(bikeID, email, start);
        System.out.println("Bike " + bikeID + " reserved.");

        System.out.println("\nActive rentals:");
        rentalService.showActiveRentals();

        rentalService.endRental(bikeID);
        bikeService.returnBike(bikeID);
        System.out.println("\nTrip ended.");

        System.out.println("\nAfter trip end:");
        rentalService.showActiveRentals();
    }
}