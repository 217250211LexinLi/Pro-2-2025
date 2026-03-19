import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
public class UserRegistration {
public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
public static final double VIP_DISCOUNT_UNDER_18 = 20.0;
public static final double VIP_BASE_FEE = 100.0;
public String fullName;
public String emailAddress;
public String dateOfBirth;
public long cardNumber;
public String cardProvider;
public String cardExpiryDate;
public int cvv;
public double feeToCharge;
public String userType;
public boolean emailValid;
public boolean minorAndBirthday;
public boolean minor;
public boolean ageValid;
public boolean cardNumberValid;
public boolean cardStillValid;
public boolean validCVV;

private Scanner scanner = new Scanner(System.in);

public void registration() {
System.out.println("Welcome to the ERyder Registration.");
System.out.println("Here are your two options:");
System.out.println("1. Register as a Regular User");
System.out.println("2. Register as a VIP User");
System.out.print("Please enter your choice (1 or 2): ");
int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            userType = "Regular User";
        } else {
            userType = "VIP User";
        }
        System.out.print("Please enter your full name: ");
        fullName = scanner.nextLine();

        System.out.print("Please enter your email address: ");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail();

        System.out.print("Please enter your date of birth (YYYY-MM-DD): ");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.print("Please enter your card number: ");
        cardNumber = scanner.nextLong();
        cardNumberValid = analyseCardNumber();

        System.out.print("Please enter your card expiry date (MM/YY): ");
        cardExpiryDate = scanner.next();
        cardStillValid = analyseCardExpiryDate();

        System.out.print("Please enter your card CVV: ");
        cvv = scanner.nextInt();
        validCVV = analyseCVV();
        finalCheckpoint();
        scanner.close();
    }
private boolean analyseEmail() {
        if (emailAddress.contains("@") && emailAddress.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to the start of the registration");
            registration();
            return false;
        }
    }
private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        int age = period.getYears();
        boolean isBirthday = (dob.getMonthValue() == currentDate.getMonthValue() && dob.getDayOfMonth() == currentDate.getDayOfMonth());

        if (userType.equals("VIP User")) {
            if (isBirthday && age <= 18 && age > 12) {
                System.out.println("Happy Birthday!");
                System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
            } else if (!isBirthday && age <= 18 && age > 12) {
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            }
        }

        if (age <= 12 || age >= 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day");
            System.exit(0);
        }
        return true;
    }

    private boolean analyseCardNumber() {
        String cardNumStr = String.valueOf(cardNumber);
        int length = cardNumStr.length();
        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));

        if ((length == 13 || length == 15 || length == 16) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
            return true;
        } else if (length == 16 && ((firstTwoDigits >= 51 && firstTwoDigits <= 55) || (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            return true;
        } else if (length == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            return true;
        } else {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.");
            System.out.println("Going back to the start of the registration.");
            registration();
            return false;
        }
    }

    private boolean analyseCardExpiryDate() {
        int month = Integer.parseInt(cardExpiryDate.substring(0, 2));
        int year = Integer.parseInt(cardExpiryDate.substring(3, 5)) + 2000;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use a different card.");
            System.out.println("Going back to the start of the registration process...");
            registration();
            return false;
        }
    }

    private boolean analyseCVV() {
        String cvvStr = String.valueOf(cvv);
        if ((cardProvider.equals("American Express") && cvvStr.length() == 4) || ((cardProvider.equals("VISA") || cardProvider.equals("MasterCard")) && cvvStr.length() == 3)) {
            System.out.println("Card CVV is valid.");
            return true;
        } else {
            System.out.println("Invalid CVV for the given card.");
            System.out.println("Going back to the start of the registration process.");
            registration();
            return false;
        }
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
            if (!emailValid) System.out.println("Invalid email address");
            if (!ageValid) System.out.println("Invalid age");
            if (!cardNumberValid) System.out.println("Invalid card number");
            if (!cardStillValid) System.out.println("Card has expired");
            if (!validCVV) System.out.println("Invalid CVV");
            System.out.println("Going back to the start of the registration process.");
            registration();
        }
    }

    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18 / 100);
        } else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardNumStr = String.valueOf(cardNumber);
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);
        System.out.println("Thank you for your payment.");
        System.out.printf("A fee of %.2f has been charged to your card ending with ****%s%n", feeToCharge, lastFourDigits);
    }

    @Override
    public String toString() {
        String cardNumberStr = String.valueOf(cardNumber);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;

     return "Registration successful! Here are your details:\n" +
                "User Type: " + userType + "\n" +
                "Full Name: " + fullName + "\n" +
                "Email Address: " + emailAddress + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Card Number: " + censoredNumber + "\n" +
                "Card Provider: " + cardProvider + "\n" +
                "Card Expiry Date: " + cardExpiryDate;
    }
}
