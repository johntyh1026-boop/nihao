package CLI;
import entity.*;
import action.CustomerAction;
import java.util.ArrayList;
public class CustomerMenu extends Menu {
    
    public CustomerMenu(Customer customer, CustomerAction action) {
        super(customer, action);
        System.out.println("Welcome! " + customer.getName());
    }
    Customer customer = (Customer) user;
    CustomerAction action = (CustomerAction) userAction;

    public void runMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Customer: " + customer.getName() + " ===");
            System.out.println("=== ID: " + customer.getId() + " ===");
            System.out.println("===================================================");
            System.out.println("1. Search Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. Buy Snacks");
            System.err.println("4. About My Account");
            System.out.println("0. Log out");
            System.out.println("===================================================");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();
            
            switch (choice) {
                case "1":
                    searchMovies();
                    break;
                case "2":
                    //todo
                    break;
                case "3":
                    // buySnacks();
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
    private void searchMovies() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Search Movies ===");
            System.out.println("1. Search Popular Movies");
            System.out.println("2. Search movie name");
            System.out.println("3. Search History");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1":
                    ArrayList<MovieSales> popularMovies = action.getMostPopularMovies();
                    Table.showPopularMovies(popularMovies);
                    break;
                case "2":
                    System.out.print("Enter movie name keyword: ");
                    String keyword = input.nextLine().trim();
                    ArrayList<Movie> movies = action.searchMoviesByTitle(keyword, customer);
                    Table.showAllMovie(movies);
                    break;
                case "3":
                    searchSearchHistory();
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
    private void searchSearchHistory() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Search History ===");
            System.out.println("1. View Search History");
            System.out.println("2. Clear Search History");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1":
                    ArrayList<SearchLog> logs = action.getSearchHistory(customer);
                    Table.showSearchHistory(logs);
                    break;
                case "2":
                    boolean success = action.clearSearchHistory(customer);
                    if (success) {
                        System.out.println("Search history cleared successfully.");
                    } else {
                        System.out.println("Failed to clear search history.");
                    }
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
            System.out.println("1. Ticket History");
            System.out.println("2. Order History");
            System.out.println("3. Personal Details");
            System.out.println("4. Profile Settings");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1":
                    ArrayList<Ticket> tickets = action.getTicketHistory(customer);
                    Table.showTicketTable(tickets);
                    break;
                case "2":
                    ArrayList<Order> orders = action.getOrderHistory(customer);
                    Table.showOrderTable(orders);
                    break;
                case "3":
                    System.out.println("\n=== Personal Details ===");
                    System.out.println(customer.toString());
                    System.out.println("===================================================");
                    break;
                case "4":
                    profileSettings(customer);
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



}