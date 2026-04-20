package action;
import entity.*;
import tools.Tool;
import java.util.ArrayList;

public class CustomerAction extends UserAction {
    public CustomerAction(database.DatabaseSystem db) {
        super(db);
    }

//=====================================Account Management=================================================
    public boolean register(String name, String phone, String pin) {
        //validation
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
        
        return db.addCustomer(name, phone, pin);
    }

    public Customer login(String phone, String pin) {
        return db.verifyCustomerLogin(phone, pin);
    }
//=====================================Loyalty Points Management=================================================
    public boolean processEarnPoints(Customer customer, int pointsToEarn) {
        if (pointsToEarn <= 0) return false;

        boolean dbSuccess = db.updateCustomerPoints(customer.getPhoneNumber(), pointsToEarn);
        
        if (dbSuccess) {
            customer.addpoint(pointsToEarn);
            return true;
        }
        return false;
    }

    public boolean processUsePoints(Customer customer, int pointsToSpend) {
        if (customer.getLoyaPoints() < pointsToSpend) {
            return false; 
        }

        boolean dbSuccess = db.deductCustomerPoints(customer.getPhoneNumber(), pointsToSpend);

        if (dbSuccess) {
            customer.deductCustomerPoints(pointsToSpend);
            return true;
        }
        return false;
    }
//=====================================Ticket Booking and Snack Purchasing=================================================
    public void processBuyTicket(Customer customer) {
    }
//=====================================Movie Search and History=================================================
    public ArrayList<MovieSales> getMostPopularMovies() {
        return db.getMostPopularMovies();
    }
    public ArrayList<Movie> searchMoviesByTitle(String title, Customer customer) {
        return db.searchMoviesByTitle(title, customer.getId());
    }
    public ArrayList<SearchLog> getSearchHistory(Customer customer) {
        return db.getAllSearchLogs(customer.getId());
    }
    public boolean clearSearchHistory(Customer customer) {
        return db.clearAllSearchLogs(customer.getId());
    }
//=====================================ticket and order history=================================================
    public ArrayList<Ticket> getTicketHistory(Customer customer) {
        return db.getTicketsByCustomer(customer);
    }
    public ArrayList<Order> getOrderHistory(Customer customer) {
        return db.getOrders(customer);
     }
}