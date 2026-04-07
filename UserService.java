import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();

    public boolean isRegistered(String email) {
        return true;
    }

    public void register() {
        System.out.println("User registration completed successfully.");
    }

    public RegisteredUsers addNewUser(String fullName, String emailAddress, String dateOfBirth,
                                      String cardNumber, String cardExpiryDate, String cardProvider,
                                      String cvv, String userType, String[] lastThreeTrips) {
        RegisteredUsers newUser;

        if (userType.equalsIgnoreCase("VIP")) {
            newUser = new VIPUser(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
        } else {
            newUser = new RegularUser(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
        }

        registeredUsersList.add(newUser);
        return newUser;
    }

    public List<RegisteredUsers> getRegisteredUsersList() {
        return registeredUsersList;
    }
}