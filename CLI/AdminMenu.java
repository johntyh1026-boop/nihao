package CLI;

import entity.*;
import action.AdminAction;
import tools.Tool;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class AdminMenu extends Menu {
    public AdminMenu(Admin admin, AdminAction action) {
        super(admin, action);
        System.out.println("Welcome! " + admin.getName());
    }

    Admin admin = (Admin) user;
    AdminAction action = (AdminAction) userAction;

    // ===================================================
    // MAIN MENU
    // ===================================================
    public void runMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Admin: " + admin.getName() + " ===");
            System.out.println("=== ID: " + admin.getId() + " ===");
            System.out.println("===================================================");
            System.out.println("1. Item Manager");
            System.out.println("2. Movie Manager");
            System.out.println("3. Hall Manager");
            System.err.println("4. About My Account");
            System.out.println("0. Log out");
            System.out.println("===================================================");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1":
                    runItemManager();
                    break;
                case "2":
                    runMovieManager();
                    break;
                case "3":
                    runHallManager();
                    break;
                case "4":
                    aboutMyAccount();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

    private void aboutMyAccount() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== My Account ===");
            System.out.println("1. Personal Details");
            System.out.println("2. Profile Settings");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1":
                    admin.toString();
                    break;
                case "2":
                    profileSettings(admin);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

    // ===================================================
    // ITEM MANAGER
    // ===================================================
    private void runItemManager() {
        boolean running = true;
        while (running) {
            System.out.println("\n-----------Admin Menu---------");
            System.out.println("| 1. Add/Update item           |");
            System.out.println("| 2. Remove item               |");
            System.out.println("| 3. Check item                |");
            System.out.println("| 0. Exit                      |");
            System.out.println("-".repeat(25));
            System.out.print("Enter to select(1-3, 0 to exit): ");
            String admin_selection = input.nextLine().trim();

            switch (admin_selection) {
                case "1":
                    runAddItemMenu();
                    break;
                case "2":
                    runRemoveItemMenu();
                    break;
                case "3":
                    ArrayList<Popcorn> popcorns = action.getAllPopcorn();
                    ArrayList<Drink> drinks = action.getAllDrink();
                    Table.showProductTable(popcorns, drinks);
                    break;
                case "0":
                    System.out.println("Exiting Item Manager...");
                    running = false;
                    break;
                default:
                    System.out.println("Wrong Input: Please enter number 1-3 or 0!");
                    break;
            }
        }
    }

    private void runAddItemMenu() {
        boolean running = true;
        String flavor;
        double price;
        while (running) {
            System.out.println("\n" + "-".repeat(32));
            System.out.println("--------Item Menu--------");
            System.out.println("| 1. Popcorn Flavor      |");
            System.out.println("| 2. Drink Flavor        |");
            System.out.println("| 3. Update Popcorn Price|");
            System.out.println("| 4. Update Drink Price  |");

            System.out.println("| 0. Return              |");
            System.out.println("-".repeat(25));
            System.out.print("Enter to select(1-4, 0 to return): ");
            String item_choose = input.nextLine().trim();

            switch (item_choose) {
                case "1":     
                    System.out.print("Enter new popcorn flavor(0 to cancel): ");
                    flavor = input.nextLine().trim();
                    if (flavor.equals("0")) {
                        System.out.println("Operation cancelled.");
                        break;
                    }
                    price = Tool.getValidDouble(input, "Enter the price for this popcorn flavor: ");
                    if (action.processAddPopcorn(flavor, price)) {
                        System.out.println("Success: Popcorn flavor added.");
                    } else {
                        System.out.println("Error: Failed to add popcorn flavor (Empty or Duplicate).");
                    }
                    break;
                case "2":
                    System.out.print("Enter new drink flavor(0 to cancel): ");
                    flavor = input.nextLine().trim();
                    if (flavor.equals("0")) {
                        System.out.println("Operation cancelled.");
                        break;
                    }
                    price = Tool.getValidDouble(input, "Enter the price for this drink flavor: ");
                    if (action.processAddDrink(flavor, price)) {
                        System.out.println("Success: Drink flavor added.");
                    } else {
                        System.out.println("Error: Failed to add drink flavor (Empty or Duplicate).");
                    }
                    break;
                case "3":
                    ArrayList<Popcorn> popcorns = action.getAllPopcorn();
                    Table.showAllPopcorn(popcorns);
                    System.out.print("Enter the popcorn flavor to update(0 to cancel): ");
                    flavor = input.nextLine().trim();
                    if (flavor.equals("0")) {
                        System.out.println("Operation cancelled.");
                        break;
                    }
                    price = Tool.getValidDouble(input, "Enter the new price for this popcorn flavor: ");
                    if (action.processUpdatePopcornPrice(flavor, price)) {
                        System.out.println("Success: Popcorn price updated.");
                    } else {
                        System.out.println("Error: Failed to update popcorn price (Flavor not found).");
                    }
                    break;
                case "4":
                    ArrayList<Drink> drinks = action.getAllDrink();
                    Table.showAllDrink(drinks);
                    System.out.print("Enter the drink flavor to update(0 to cancel): ");
                    flavor = input.nextLine().trim();
                    if (flavor.equals("0")) {
                        System.out.println("Operation cancelled.");
                        break;
                    }
                    price = Tool.getValidDouble(input, "Enter the new price for this drink flavor: ");
                    if (action.processUpdateDrinkPrice(flavor, price)) {
                        System.out.println("Success: Drink price updated.");
                    } else {
                        System.out.println("Error: Failed to update drink price (Flavor not found).");
                    }
                    break;

                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("WrongInput: Please enter number 1-2 or 0!");
                    break;
            }
        }
    }

    private void runRemoveItemMenu() {
        boolean running = true;
        String flavor;
        while (running) {
            System.out.println("\n" + "-".repeat(32));
            System.out.println("--------Item Menu--------");
            System.out.println("| 1. Popcorn Flavor     |");
            System.out.println("| 2. Drink Flavor       |");
            System.out.println("| 0. Return             |");
            System.out.println("-".repeat(25));
            System.out.print("Enter to select(1-2, 0 to return): ");
            String item_choose = input.nextLine().trim();

            switch (item_choose) {
                case "1":
                    ArrayList<Popcorn> popcorns = action.getAllPopcorn();
                    Table.showAllPopcorn(popcorns);
                    System.out.print("Enter the popcorn flavor to remove: ");
                    flavor = input.nextLine().trim();
                    if (action.processRemovePopcorn(flavor)) {
                        System.out.println("Success: Popcorn flavor removed.");
                    } else {
                        System.out.println("Error: Failed to remove popcorn flavor (Flavor not found).");
                    }
                    break;
                case "2":
                    ArrayList<Drink> drinks = action.getAllDrink();
                    Table.showAllDrink(drinks);
                    System.out.print("Enter the drink flavor to remove: ");
                    flavor = input.nextLine().trim();
                    if (action.processRemoveDrink(flavor)) {
                        System.out.println("Success: Drink flavor removed.");
                    } else {
                        System.out.println("Error: Failed to remove drink flavor (Flavor not found).");
                    }
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("WrongInput: Please enter number 1-2 or 0!");
                    break;
            }
        }
    }
    // ===================================================
    // MOVIE MANAGER
    // ===================================================
    private void runMovieManager() {
        ArrayList<Movie> movies = action.getAllMovie();
        boolean running = true;
        while (running) {
            System.out.println("\n=== Movie Manager ===");
            System.out.println("===================================================");
            System.out.println("1. View All Movies");
            System.out.println("2. Add New Movie");
            System.out.println("3. Remove Movie");
            System.out.println("4. Schedule Showtime(Once add schedule,cannot remove it)");
            System.out.println("0. Back to Main Menu");
            System.out.println("===================================================");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    movies = action.getAllMovie();
                    Table.showAllMovie(movies);
                    System.out.print("Press enter to continue...");
                    input.nextLine();
                    break;
                case "2":
                    System.out.println("\n=== Add New Movie ===");
                    System.out.print("Enter movie title: ");
                    String title = input.nextLine().trim();

                    int durationMin = Tool.getValidInt(input, "Enter the duration of the Movie (Min): ");

                    if (action.processAddMovie(title, durationMin)) {
                        System.out.println("Success: Movie added.");
                    } else {
                        System.out.println("Error: Failed to add movie (Duplicate Title or Invalid Duration).");
                    }
                    break;
                case "3":
                    movies = action.getAllMovie();
                    Table.showAllMovie(movies);
                    System.out.println("\n=== Remove Movie ===");
                    int movieId = Tool.getValidInt(input, "Enter the Movie ID to remove (0 to cancel): ");
                    if (movieId == 0) {
                        System.out.println("Operation cancelled.");
                        return;
                    }
                    if (action.processRemoveMovie(movieId)) {
                        System.out.println("Success: Movie removed.");
                    } else {
                        System.out.println("Error: Movie ID not found.");
                    }
                    break;
                case "4":
                    ArrayList<Hall> halls = action.getAllHallsWithSeatCount();
                    Table.showAllHallsTable(halls);
                    int hallId = Tool.getValidInt(input, "Enter the Hall ID(Digit only) to schedule (0 to cancel): ");
                    if (hallId == 0) {
                        System.out.println("Operation cancelled.");
                        return;
                    }
                    Hall selectedHall = action.getHall(hallId);
                    if (selectedHall == null) {
                        System.out.println("Error: Hall ID not found.");
                        break;
                    }
                    movies = action.getAllMovie();
                    Table.showAllMovie(movies);
                    System.out.println("\n=== Schedule Showtime ===");
                    int scheduleMovieId = Tool.getValidInt(input, "Enter the Movie ID to schedule (0 to cancel): ");

                    if (scheduleMovieId == 0) {
                        System.out.println("Operation cancelled.");
                        return;
                    }
                    Movie selectedMovie = action.getMovie(scheduleMovieId);
                    if (selectedMovie == null) {
                        System.out.println("Error: Movie ID not found.");
                        break;
                    }

                    LocalDateTime showtime = Tool.readFutureTime(input, "Enter the showtime (Format: yyyy-MM-dd HH:mm): ");
                    double basePrice = Tool.getValidDouble(input, "Enter the base price for this showtime: ");

                    if (action.processAddSchedule(selectedMovie, selectedHall, showtime, basePrice)) {
                        System.out.println("Success: Showtime scheduled.");
                    } else {
                        System.out.println("Error: Failed to schedule showtime (Invalid format or Movie ID).");
                    }
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Error: Invalid Option!");
                    break;
            }
        }
    }
    private void runHallManager() {
        ArrayList<Hall> halls = action.getAllHallsWithSeatCount();
        boolean running = true;
        while (running) {
            System.out.println("\n=== Hall Manager ===");
            System.out.println("===================================================");
            System.out.println("1. View All Halls");
            System.out.println("2. Add New Hall");
            System.out.println("3. View Hall Movie Schedule");
            System.out.println("0. Back to Main Menu");
            System.out.println("===================================================");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    
                    Table.showAllHallsTable(halls);
                    System.out.print("Press enter to continue...");
                    input.nextLine();
                    break;
                case "2":
                    System.out.println("\n=== Add New Hall ===");
                    System.out.print("Enter hall type (0 to cancel): ");
                    String hallType = input.nextLine().trim();
                    if(hallType.equals("0")){
                        System.out.println("Operation cancelled.");
                        break;
                    }
                    int seatRows = Tool.getValidInt(input, "Enter the number of seat rows: ");
                    int seatsPerRow = Tool.getValidInt(input, "Enter the number of seats per row: ");

                    if (action.processAddHall(hallType, seatRows, seatsPerRow)) {
                        System.out.println("Success: Hall added.");
                    } else {
                        System.out.println("Error: Failed to add hall");
                    }
                    break;
                case "3":
                    Table.showAllHallsTable(halls);
                    int hallId = Tool.getValidInt(input,"Enter hall id(digit only): ");
                    if(hallId == 0){
                        break;
                    }
                    Hall target = action.getHall(hallId);
                    if(target == null){
                        System.out.println("HAll Not found");
                    }
                    ArrayList<Schedule> schedules = action.getHallSchedule(target);
                    Table.showAllSchedule(schedules);

                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Error: Invalid Option!");
                    break;
            }
        }
    }
}