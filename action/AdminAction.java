package action;
import java.util.ArrayList;
import database.DatabaseSystem;
import entity.*;
import tools.Tool;
import java.time.LocalDateTime;
public class AdminAction extends UserAction {
    public AdminAction(DatabaseSystem db) {
        super(db);
    }
//=====================================Account Management=================================================
    public boolean register(String name, String phone, String pin) {
        if (name == null || name.trim().isEmpty() || pin == null || pin.length() != 6) {
            System.out.println("Action Error: Invalid input format.");
            return false; 
        }
        if(!Tool.isvalidPin(pin)){
            System.out.println("Action Error: Invalid pin format.");
            return false; 
         }
        if(Tool.hasNum(name)){
            System.out.println("Action Error: Name cannot contain numbers.");
            return false;
        }
        if(!Tool.isvalidphone(phone)){
            System.out.println("Action Error: Invalid phone number format.");
            return false;
        }
        return db.addAdmin(name, phone, pin);
    }

    public Admin login(String phone, String pin) {
        return db.verifyAdminLogin(phone, pin);
    }
//=====================================Customer Management=================================================
    public ArrayList<Customer> getAllCustomers() {
        return db.getAllCustomer();
    }
//=====================================Movie and Snack Management=================================================
//========================movie=============================
    public boolean processAddMovie(String title, int durationMin) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Action Error: Movie title cannot be empty.");
            return false;
        }
        if (durationMin < 10) {
            System.out.println("Action Error: Movie duration is too short!");
            return false;
        }
        return db.addMovie(title, durationMin);
    }

    public boolean processRemoveMovie(int movieId) {
        return db.removeMovie(movieId);
    }
//========================popcorn=============================
    public boolean processAddPopcorn(String flavor,double price) {
        if (price <= 0) {
            System.out.println("Action Error: Price must be greater than zero.");
            return false;
        }
        if (flavor == null || flavor.trim().isEmpty()) {
            return false;
        }
        if(Tool.isNum(flavor)||Tool.hasNum(flavor)) {
            System.out.println("Action Error: Popcorn flavor cannot be a number.");
            return false;
        }
        return db.addPopcorn(flavor, price);
    }

    public boolean processRemovePopcorn(String flavor) {
        return db.removePopcorn(flavor);
    }
    public boolean processUpdatePopcornPrice(String flavor, double newPrice) {
        if (newPrice <= 0) {
            System.out.println("Action Error: Price must be greater than zero.");
            return false;
        }
        return db.updatePopcornPrice(flavor, newPrice);
    }
//========================drink=============================
    public boolean processAddDrink(String flavor, double price) {
        if (price <= 0) {
            System.out.println("Action Error: Price must be greater than zero.");
            return false;
        }
        if (flavor == null || flavor.trim().isEmpty()) {
            return false;
        }
        if (Tool.isNum(flavor)||Tool.hasNum(flavor)) {
            System.out.println("Action Error: Drink flavor cannot be a number.");
            return false;
        }
        return db.addDrink(flavor, price);
    }

    public boolean processRemoveDrink(String flavor) {
        return db.removeDrink(flavor);
    }
    public boolean processUpdateDrinkPrice(String flavor, double newPrice) {
        if (newPrice <= 0) {
            System.out.println("Action Error: Price must be greater than zero.");
            return false;
        }
        return db.updateDrinkPrice(flavor, newPrice);
    }
//=====================================Hall and Seat Management=================================================
    public boolean processAddHall(String hallType, int numberOfRows, int numberOfSeatsPerRow) {

        if (hallType == null || hallType.trim().isEmpty()) {
            System.out.println("Action Error: Hall type cannot be empty.");
            return false;
        }
        if (Tool.isNum(hallType)) {
            System.out.println("Action Error: Hall type cannot be a number.");
            return false;
        }
        if (numberOfRows <= 0 || numberOfSeatsPerRow <= 0) {
            System.out.println("Action Error: Number of rows and seats per row must be greater than zero.");
            return false;
        }
        return db.addHall(hallType, numberOfRows, numberOfSeatsPerRow);
    }
//=====================================Schedule Management=================================================
    public boolean processAddSchedule(Movie movie, Hall hall, LocalDateTime showTime, double ticketPrice) {
        if(ticketPrice <= 0) {
            System.out.println("Action Error: Ticket price must be greater than zero.");
            return false;
        }
        return db.addSchedule(movie, hall.getHallId(), showTime, ticketPrice);
    }

}