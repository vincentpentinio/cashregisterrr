asfsaf
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.regex.*;

public class CashRegister {

    static String[] menu = {"Burger", "Pizza", "Pasta", "Fries", "Soda"};
    static double[] prices = {5.99, 8.99, 6.49, 2.99, 1.99};
    static String[] orderedItems = new String[100];
    static int[] quantity = new int[100];
    static double[] itemTotal = new double[100];
    static int orderCount = 0;
    static double total = 0;
    static double taxRate = 0.10; // 10% Tax

    static Scanner input = new Scanner(System.in);

    static ArrayList<String> usernames = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();

    public static void main(String[] args) {
        signup();
        login();
        welcome();
        showMenu();
        takeOrder();
        payment();
        printReceipt();
    }

    // User Signup
    public static void signup() {
        System.out.println("===== USER SIGNUP =====");
        String username, password;
        boolean valid = false;

        do {
            System.out.print("Create a username (alphanumeric, 5-15 characters): ");
            username = input.nextLine();
            if (!username.matches("^[a-zA-Z0-9]{5,15}$")) {
                System.out.println("Invalid username format.");
                continue;
            }

            System.out.print("Create a password (8-20 chars, 1 uppercase, 1 digit): ");
            password = input.nextLine();
            if (!password.matches("^(?=.*[A-Z])(?=.*\\d).{8,20}$")) {
                System.out.println("Invalid password format.");
                continue;
            }

            usernames.add(username);
            passwords.add(password);
            valid = true;

        } while (!valid);

        System.out.println("Signup successful!\n");
    }

    // User Login
    public static void login() {
        System.out.println("===== USER LOGIN =====");
        String username, password;

        while (true) {
            System.out.print("Enter username: ");
            username = input.nextLine();
            System.out.print("Enter password: ");
            password = input.nextLine();

            if (usernames.contains(username)) {
                int index = usernames.indexOf(username);
                if (passwords.get(index).equals(password)) {
                    System.out.println("Login successful!\n");
                    break;
                }
            }
            System.out.println("Incorrect username or password. Try again.\n");
        }
    }

    // Welcome Message
    public static void welcome() {
        System.out.println("===== WELCOME TO RESTAURANT =====");
    }

    // Display Menu
    public static void showMenu() {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i] + " - $" + prices[i]);
        }
        System.out.println("============================");
    }

    // Take Order
    public static void takeOrder() {
        char moreOrder;
        do {
            System.out.print("Enter item number: ");
            int itemNumber = input.nextInt();

            if (itemNumber > 0 && itemNumber <= menu.length) {
                System.out.print("Enter quantity: ");
                int qty = input.nextInt();

                orderedItems[orderCount] = menu[itemNumber - 1];
                quantity[orderCount] = qty;
                itemTotal[orderCount] = prices[itemNumber - 1] * qty;
                total += itemTotal[orderCount];

                System.out.println(menu[itemNumber - 1] + " x " + qty + " = $" + itemTotal[orderCount]);
                orderCount++;
            } else {
                System.out.println("Invalid Item! Try Again.");
            }

            System.out.print("Do you want to order more? (y/n): ");
            moreOrder = input.next().charAt(0);
        } while (moreOrder == 'y' || moreOrder == 'Y');
    }

    // Payment Function
    public static void payment() {
        double tax = total * taxRate;
        double grandTotal = total + tax;

        System.out.println("============================");
        System.out.printf("Subtotal: $%.2f%n", total);
        System.out.printf("Tax (10%%): $%.2f%n", tax);
        System.out.printf("Total Amount: $%.2f%n", grandTotal);

        double payment;
        do {
            System.out.print("Enter Payment Amount: $");
            payment = input.nextDouble();

            if (payment < grandTotal) {
                System.out.println("Not enough money, please enter more.");
            }
        } while (payment < grandTotal);

        double change = payment - grandTotal;
        System.out.printf("Payment Successful! Your Change: $%.2f%n", change);
    }

    // Print Receipt
    public static void printReceipt() {
        System.out.println("\n======= RECEIPT =======");
        System.out.println("Date: " + getDate());
        System.out.println("Receipt No: " + (int) (Math.random() * 10000));

        System.out.println("======================");
        System.out.println("Item\t\tQty\tPrice");
        System.out.println("======================");

        for (int i = 0; i < orderCount; i++) {
            System.out.printf("%-10s\t%d\t$%.2f%n", orderedItems[i], quantity[i], itemTotal[i]);
        }

        double tax = total * taxRate;
        double grandTotal = total + tax;

        System.out.println("======================");
        System.out.printf("Subtotal: $%.2f%n", total);
        System.out.printf("Tax (10%%): $%.2f%n", tax);
        System.out.printf("Total: $%.2f%n", grandTotal);
        System.out.println("Thank You! Come Again!");
        System.out.println("======================");
    }

    // Get Current Date
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}

