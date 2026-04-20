import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PrintReceipt {

    public static void printDetailedReceipt(String movieName, String hallName, ArrayList<String> seatList, double ticketPrice, ArrayList<String> snacks, double snackTotal) {

        final double SST_RATE = 0.06;
        int ticketCount = seatList.size();
        double ticketSubtotal = ticketPrice * ticketCount;
        double subtotal = ticketSubtotal + snackTotal;
        double tax = subtotal * SST_RATE;
        double total = subtotal + tax;
        
        int pointsEarned = (int)total * 10;

        LocalDateTime now = LocalDateTime.now();
        String dateTime = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        String receiptID = "TIX-" + now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        System.out.println("\n" + "=".repeat(40));
        System.out.println("         GOLDEN SCREEN CINEMA");
        System.out.println("          Official Receipt");
        System.out.println("=".repeat(40));
        System.out.println("ID   : " + receiptID);
        System.out.println("Date : " + dateTime);
        System.out.println("-".repeat(40));

        System.out.println("MOVIE    : " + movieName);
        System.out.println("HALL     : " + hallName);
        System.out.println("SEATS    : " + String.join(", ", seatList));
        System.out.println("QUANTITY : " + ticketCount);
        System.out.println("-".repeat(40));

        System.out.printf("%-25s : RM%7.2f\n", "Ticket Subtotal", ticketSubtotal);
        
        if (snacks != null && !snacks.isEmpty()) {
            System.out.println("SNACKS   :");
            for (String s : snacks) {
                System.out.println("  - " + s);
            }
            System.out.printf("%-25s : RM%7.2f\n", "Snacks Subtotal", snackTotal);
        }

        System.out.println("-".repeat(40));
        System.out.printf("%-25s : RM%7.2f\n", "Service Tax (6%)", tax);
        System.out.printf("%-25s : RM%7.2f\n", "TOTAL AMOUNT", total);
        System.out.println("-".repeat(40));
        
        System.out.println("LOYALTY POINTS EARNED : " + pointsEarned);
        System.out.println("=".repeat(40));

        System.out.println("       DIGITAL TICKET QR CODE");
        System.out.println("       [  SCAN TO VALIDATE  ]");
        System.out.println("       ▄▄▄▄▄▄▄  ▄ ▄ ▄▄▄▄▄▄▄ ");
        System.out.println("       █ ▄▄▄ █ ▀█▄█ █ ▄▄▄ █ ");
        System.out.println("       █ ███ █ █▀ █ █ ███ █ ");
        System.out.println("       █▄▄▄▄▄█ █ ▄▀ █▄▄▄▄▄█ ");
        System.out.println("       ▄▄▄ ▄▄▄▄█▀▀▀▄▄▄ ▄ ▄  ");
        System.out.println("       ▄▀▀▄▄▄▀▀▄▀ ▀▄▀▀ █ ▀▄ ");
        System.out.println("       █▀▄▄▄▄▄▀▀ █▀▀█▄▄ ▀▀▄ ");
        System.out.println("       ▄▄▄▄▄▄▄ █▀▀▄█▄▄█ ▀ ▄ ");
        System.out.println("       █ ▄▄▄ █ █ ▄▀▀▀█▄█ █▀ ");
        System.out.println("       █ ███ █ █▄█▀▄▀▄▀▄▀▄▀ ");
        System.out.println("       █▄▄▄▄▄█ █ ▀ ▀▄▄ ▀ █  ");
        System.out.println("       SCAN AT ENTRANCE HALL");
        System.out.println("=".repeat(40));
        System.out.println("      Enjoy your movie & snacks!");
    }
}
