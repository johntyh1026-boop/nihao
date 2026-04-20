package action;
import java.util.ArrayList;
import database.DatabaseSystem;
import entity.*;
import tools.Tool;

public abstract class UserAction {
    
    protected DatabaseSystem db;

    public UserAction(DatabaseSystem db) {
        this.db = db;
    }

    public boolean updateUserName(User user, String newName) {
        if(Tool.hasNum(newName)){
            System.out.println("Action Error: Name cannot contain numbers.");
            return false;
        }
         if (newName == null || newName.trim().isEmpty()) {
            System.out.println("Action Error: Invalid name format.");
            return false;
        }
        user.setName(newName);
        
        if (user instanceof Admin) {

            boolean success = db.setName(user.getPhoneNumber(), newName, "ADMIN"); 
            return success;

        } else if (user instanceof Customer) {
            boolean success = db.setName(user.getPhoneNumber(), newName, "CUSTOMER");
            return success;
        }
        return false;
    }

    public boolean updateUserPin(User user, String newPin) {
        if(!Tool.isvalidPin(newPin)){
            System.out.println("Action Error: Invalid pin format.");
            return false;
        }
        if(newPin.equals(newPin)){
            System.out.println("Action Error: New PIN cannot be the same as the old PIN.");
            return false;
        }
        user.setSixDigitPin(newPin);
        
        if (user instanceof Admin) {
            boolean success = db.setPin(user.getPhoneNumber(), newPin, "ADMIN");
            return success;
        } else if (user instanceof Customer) {
            boolean success = db.setPin(user.getPhoneNumber(), newPin, "CUSTOMER");
            return success;
        }
        return false;
    }

    //methods for every user
    public ArrayList<Movie> getAllMovies() {
        return db.getAllMovies();
    }
    public ArrayList<Movie> getAllMovie() {
        return db.getAllMovies();
    }
    public ArrayList<Popcorn> getAllPopcorn() {
        return db.getAllPopcorn();
    }
    public ArrayList<Drink> getAllDrink() {
        return db.getAllDrink();
    }
    public ArrayList<Schedule> getAllSchedule() {
        return db.getAllSchedules();
    }
    public ArrayList<Schedule> getHallSchedule(Hall targetId){
        return db.getHallSchedule(targetId);
    }
    public ArrayList<Hall> getAllHall() {
        return db.getAllHalls();
    }
    public ArrayList<Hall> getAllHallsWithSeatCount(){
        return db.getAllHallsWithSeatCount();
    }

    public Movie getMovie(int movieId){
        return db.getMovie(movieId);
    }
    public Schedule getSchedule(int scheduleId){
        return db.getSchedule(scheduleId);
    }
    public Popcorn getPopcorn(String flavor){
        return db.getPopcorn(flavor);
    }
    public Drink getDrink(String flavor){
        return db.getDrink(flavor);
    }
    public Hall getHall(int hallId){
        return db.getHall(hallId);
    }


}