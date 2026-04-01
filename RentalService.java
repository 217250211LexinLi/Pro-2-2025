import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class RentalService {
    private final LinkedList<ActiveRental> activeRentals;

    public RentalService() {
        activeRentals = new LinkedList<>();
    }

    public void startRental(String bikeID, String email, LocalDateTime startTime) {
        activeRentals.add(new ActiveRental(bikeID, email, startTime));
    }

    public void endRental(String bikeID) {
        Iterator<ActiveRental> it = activeRentals.iterator();
        while (it.hasNext()) {
            if (it.next().getBikeID().equals(bikeID)) {
                it.remove();
                break;
            }
        }
    }

    public void showActiveRentals() {
        if (activeRentals.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }
        for (ActiveRental r : activeRentals) {
            System.out.println(r);
        }
    }
}