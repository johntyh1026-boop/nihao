import java.util.Scanner;
import database.DatabaseSystem;
import entity.*;
import action.*;
import CLI.*;

public class CinemaMain {
    static Scanner input = new Scanner(System.in);

    // init action of Admin and customer
    static AdminAction adminAction = new AdminAction(new DatabaseSystem());
    static CustomerAction customerAction = new CustomerAction(new DatabaseSystem());

    public static void main(String[] args) {
        DatabaseSystem db = new DatabaseSystem();
        db.initDatabase();

        boolean running = true;
        while (running) {
            System.out.println("\n=== CINEMA ===");
            System.out.println("===================================================");
            System.out.println("1. Now Showing");
            System.out.println("2. Customer Portal");
            System.out.println("3. Admin Dashboard");
            System.out.println("0. Exit");
            System.out.println("===================================================");
            System.out.print("Enter choice: ");

            String choice = input.nextLine().trim();
            switch (choice) {
                case "1": // TODO: now showing
                    break;
                case "2":
                    customerMainMenu();
                    break;
                case "3":
                    adminMainMenu();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        System.out.println("Goodbye!");
    }

    // ==========================================
    // ADMIN PORTAL
    // ==========================================
    public static void adminMainMenu() {
        boolean running = true;
        String name;
        String phone;
        String pin;
        System.out.println(
                "\n╔════════════════════════════╗\n║         Admin Menu         ║\n╚════════════════════════════╝");
        while (running) {
            System.out.println("\n=== Admin ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Main menu");
            System.out.println("===================================================");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter Name: ");
                    name = input.nextLine().trim();
                    System.out.print("Enter Phone: ");
                    phone = input.nextLine().trim();
                    System.out.print("Enter PIN: ");
                    pin = input.nextLine().trim();

                    if (adminAction.register(name, phone, pin)) {
                        System.out.println("Success: Admin registered.");
                    } else {
                        System.out.println("Error: Registration failed.");
                    }
                    break;
                case "2":
                    System.out.print("Enter Phone: ");
                    phone = input.nextLine().trim();
                    System.out.print("Enter PIN: ");
                    pin = input.nextLine().trim();
                    Admin currentAdmin = adminAction.login(phone, pin);
                    if (currentAdmin != null) {
                        AdminMenu menu = new AdminMenu(currentAdmin, adminAction);
                        menu.runMenu();
                    }else {
                        System.out.println("Error: Login failed.");
                    }
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ==========================================
    // CUSTOMER PORTAL
    // ==========================================
    public static void customerMainMenu() {
        String name;
        String phone;
        String pin;
        boolean running = true;
        System.out.println(
                "\n╔════════════════════════════╗\n║       Customer Menu        ║\n╚════════════════════════════╝");
        while (running) {
            System.out.println("\n=== Customer ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Main menu");
            System.out.println("===================================================");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter Name: ");
                    name = input.nextLine().trim();
                    System.out.print("Enter Phone: ");
                    phone = input.nextLine().trim();
                    System.out.print("Enter PIN: ");
                    pin = input.nextLine().trim();

                    if (customerAction.register(name, phone, pin)) {
                        System.out.println("Success: Customer registered.");
                    } else {
                        System.out.println("Error: Registration failed.");
                    }
                    break;
                case "2":
                    System.out.print("Enter Phone: ");
                    phone = input.nextLine().trim();
                    System.out.print("Enter PIN: ");
                    pin = input.nextLine().trim();

                    Customer currentCustomer = customerAction.login(phone, pin);
                    if (currentCustomer != null) {
                        CustomerMenu menu = new CustomerMenu(currentCustomer, customerAction);
                        menu.runMenu();
                    }else {
                        System.out.println("Error: Login failed.");
                    }
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
