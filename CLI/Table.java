package CLI;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import entity.*;

public class Table {

    static Scanner input = new Scanner(System.in);

    public static void showProductTable(ArrayList<Popcorn> popcornFlavor, ArrayList<Drink> drinkFlavor) {
        System.out.println("\n=================================================");
        System.out.println("                F&B PRODUCT MENU               ");
        System.out.println("=================================================");

        // 1. 打印爆米花专区
        System.out.println("\n[ POPCORN ]");
        System.out.printf("| %-4s | %-25s | %-10s |\n", "No.", "Flavor", "Price");
        System.out.println("-".repeat(47));
        
        if (popcornFlavor.isEmpty()) {
            System.out.println("| " + " ".repeat(10) + "No popcorn available" + " ".repeat(13) + " |");
        } else {
            for (int i = 0; i < popcornFlavor.size(); i++) {
                System.out.printf("| %-4d | %-25s | RM %-7.2f |\n", 
                                  (i + 1), 
                                  popcornFlavor.get(i).getPopcornFlavor(), 
                                  popcornFlavor.get(i).getPopcornPrice()); 
            }
        }

        System.out.println("\n[ DRINKS ]");
        System.out.printf("| %-4s | %-25s | %-10s |\n", "No.", "Flavor", "Price");
        System.out.println("-".repeat(47));
        
        if (drinkFlavor.isEmpty()) {
            System.out.println("| " + " ".repeat(11) + "No drinks available" + " ".repeat(13) + " |");
        } else {
            for (int i = 0; i < drinkFlavor.size(); i++) {
                System.out.printf("| %-4d | %-25s | RM %-7.2f |\n", 
                                  (i + 1), 
                                  drinkFlavor.get(i).getDrinkName(),
                                  drinkFlavor.get(i).getDrinkPrice()); 
            }
        }
        System.out.println("=================================================\n");
        
        System.out.print("Press enter to continue...");
        input.nextLine();
    }

    public static void showAllCustomer(ArrayList<Customer> customers){
        if (customers.isEmpty()) {
            System.out.println("No customers found in the database.");
            return;
        }

        System.out.println("\n===============" + " CUSTOMER " + "===============");
        System.out.printf("%-7s | %-15s | %-15s | %-10s | %-10s\n", "ID", "Name", "Phone", "PIN", "Points");
        System.out.println("-".repeat(65));

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.printf("%-5s | %-15s | %-15s | %-10s | %-10d\n",
                    c.getId(), c.getName(), c.getPhoneNumber(), c.getSixDigitPin(), c.getLoyaPoints());
        }
        System.out.println("=".repeat(65));
    }

    public static void showAllMovie(ArrayList<Movie> movies){
        if (movies.isEmpty()) {
            System.out.println("No movies found in the database.");
            return;
        }

        System.out.println("\n" + "=".repeat(20) + " MOVIE LIST " + "=".repeat(20));
        System.out.printf("| %-8s | %-24s | %-10s |\n", "ID", "Movie Title", "Duration");
        System.out.println("-".repeat(52));

        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            System.out.printf("| %-8s | %-24s | %-6d min |\n", 
                              m.getDisplayid(), m.getMovieTitle(), m.getDurationInMin());
        }
        System.out.println("=".repeat(52));

    }

    public static void showAllPopcorn(ArrayList<Popcorn> popcornFlavor){
        if (popcornFlavor.isEmpty()) {
            System.out.println("No Popcorn flavors available.");
            return;
        }
        System.out.println("\n" + "=".repeat(20) + " POPCORN FLAVOR LIST " + "=".repeat(20));
        for (int i = 0; i < popcornFlavor.size(); i++) {
            System.out.printf("%d. %s - $%.2f\n", i+1, popcornFlavor.get(i).getPopcornFlavor(), popcornFlavor.get(i).getPopcornPrice());
        }
        System.out.println("=".repeat(60));
    }

    public static void showAllDrink(ArrayList<Drink> drinkFlavor){
        if (drinkFlavor.isEmpty()) {
            System.out.println("No Drink flavors available.");
            return;
        }
        System.out.println("\n" + "=".repeat(20) + " DRINK FLAVOR LIST " + "=".repeat(20));
        for (int i = 0; i < drinkFlavor.size(); i++) {
            System.out.printf("%d. %s - $%.2f\n", i+1, drinkFlavor.get(i).getDrinkName(), drinkFlavor.get(i).getDrinkPrice());
        }
        System.out.println("=".repeat(60));
    }

    public static void showAllSchedule(ArrayList<Schedule> schedules){
        if (schedules.isEmpty()) {
            System.out.println("No schedules found in the database.");
            return;
        }

        System.out.println("\n" + "=".repeat(45) + " SCHEDULE LIST " + "=".repeat(38));
        System.out.printf("| %-8s | %-24s | %-20s | %-20s | %-10s |\n", "ID", "Movie Title", "Start Time", "End Time", "Price");
        System.out.println("-".repeat(98));

        for (int i = 0; i < schedules.size(); i++) {
            Schedule s = schedules.get(i);
            System.out.printf("| %-8s | %-24s | %-20s | %-20s | $%-9.2f |\n", 
                              s.getDisplayId(), s.getMovie().getMovieTitle(), s.getStartTime(), s.getEndTime(), s.getPrice());
        }
        System.out.println("=".repeat(98));
    }

    public static void showPopularMovies(ArrayList<MovieSales> popularMovies) {
        if (popularMovies.isEmpty()) {
            System.out.println("No popular movies data available.");
            return;
        }

        System.out.println("\n=================================================");
        System.out.println("                MOST POPULAR MOVIES               ");
        System.out.println("=================================================");

        System.out.printf("%-5s | %-25s | %-10s\n", "Rank", "Movie Title", "Sold");
        System.out.println("-------------------------------------------------");
        
        for (int i = 0; i < popularMovies.size(); i++) {
            MovieSales ms = popularMovies.get(i);
            
            System.out.printf("%-5d | %-25s | %-10d\n", 
                              (i + 1), 
                              ms.getMovieTitle(), 
                              ms.getTicketsSold());
        }
        System.out.println("=================================================\n");
    }

    public static void showSearchHistory(ArrayList<SearchLog> logs) {
        if (logs.isEmpty()) {
            System.out.println("No search history found.");
            return;
        }

        System.out.println("\n" + "=".repeat(20) + " SEARCH HISTORY " + "=".repeat(20));
        System.out.printf("| %-5s | %-30s | %-20s |\n", "No.", "Search Keyword", "Search Time");
        System.out.println("-".repeat(60));

        for (int i = 0; i < logs.size(); i++) {
            SearchLog log = logs.get(i);
            System.out.printf("| %-5d | %-30s | %-20s |\n", 
                              (i + 1), 
                              log.getKeyword(), 
                              log.getSearchTime());
        }
        System.out.println("=".repeat(60));
    }
    public static void showTicketTable(ArrayList<Ticket> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            System.out.println("No ticket history found.");
            return;
        }
        System.out.println("\n=========================================================================================");
        System.out.println("                                  YOUR TICKET HISTORY                                  ");
        System.out.println("=========================================================================================");

        System.out.printf("%-5s | %-25s | %-10s | %-6s | %-18s | %-8s\n", 
                "ID", "Movie Title", "Hall", "Seat", "Show Time", "Price");
        System.out.println("-----------------------------------------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // 4. 循环打印实体类数据
        for (Ticket t : tickets) {
            String seatInfo = t.getSeat().getSeatRow() + String.valueOf(t.getSeat().getSeatNumber());

            String timeStr = t.getShowTime().format(formatter); 

            System.out.printf("%-5d | %-25s | %-10s | %-6s | %-18s | RM %-5.2f\n", 
                    t.getTicketId(), 
                    t.getMovieTitle(),  
                    t.getHallName(),    
                    seatInfo, 
                    timeStr, 
                    t.getPrice());
        }
        System.out.println("=========================================================================================\n");
    }
    public static void showAllHallsTable(ArrayList<Hall> halls) {
        if (halls == null || halls.isEmpty()) {
            System.out.println("No cinema halls found.");
            return;
        }

        System.out.println("\n=================================================");
        System.out.println("                ALL CINEMA HALLS               ");
        System.out.println("=================================================");
        System.out.printf("%-10s | %-20s | %-10s\n", "Hall ID", "Hall Type", "Total Seats");
        System.out.println("-------------------------------------------------");
        
        for (Hall hall : halls) {
            System.out.printf("%-10s | %-20s | %-10d\n", 
                              hall.getDisplayId(), 
                              hall.getHallType(), 
                              hall.getTotalSeats());
        }
        System.out.println("=================================================\n");
    }
    public static void showOrderTable(ArrayList<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            System.out.println("Record not found!");
            return;
        }

        System.out.println("\n==========================================================");
        System.out.println("                     YOUR F&B ORDERS                    ");
        System.out.println("==========================================================");
        
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Order order : orders) {
            System.out.printf("Order ID: #%-5d | Time: %-16s | Total: RM %.2f\n", 
                              order.getOrderId(), 
                              order.getOrderTime().format(formatter), 
                              order.getTotalPrice());
            System.out.println("----------------------------------------------------------");
            
            // 🌟 第二层循环：遍历这张收银条里的每一个物品 (OrderItem)
            for (OrderItem item : order.getItemList()) {
                System.out.printf("  - %-20s x %-2d (RM %5.2f)\n", 
                                  item.getItemname(), 
                                  item.getQuantity(), 
                                  item.getSubtotal());
            }
            System.out.println("==========================================================");
        }
        System.out.println();
    }

}