package entity;
import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    
    private Customer customer; 
    private Seat seat;         
    private String movieTitle; 
    private String hallName;
    private LocalDateTime showTime;
    private LocalDateTime purchaseTime;
    private double price;

    public Ticket(int ticketId, Customer customer, Seat seat, String movieTitle, String hallName, LocalDateTime showTime, LocalDateTime purchaseTime, double price){
        this.ticketId = ticketId;
        this.customer = customer;
        this.seat = seat;
        this.movieTitle = movieTitle;
        this.hallName = hallName;
        this.showTime = showTime;
        this.price = price;
        this.purchaseTime = purchaseTime;
    }

    // Getters
    public int getTicketId(){ 
        return ticketId;
    }

    public Customer getCustomer(){ 
        return customer; 
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getHallName() {
        return hallName;
    }

    public Seat getSeat(){ 
        return seat;
    }

    public double getPrice() {
         return price; 
    }

    public LocalDateTime getPurchaseTime(){ 
        return purchaseTime; 
    }
    public LocalDateTime getShowTime() {
        return showTime;
    }

    public String getDisplayId() {
        return String.format("TIC-%05d", ticketId);
    }
}