package entity;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private Customer customer;
    private LocalDateTime orderTime;
    private double totalPrice;
    private ArrayList<OrderItem> itemList; 

    public Order(int orderId, Customer customer, LocalDateTime orderTime, double totalPrice) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
        this.itemList = new ArrayList<>();
    }
    public Order(Customer customer,LocalDateTime orDateTime,double totalPrice){
        this.customer = customer;
        this.orderTime = orDateTime;
        this.totalPrice = totalPrice;
    }
    public Order(int orderId, Customer customer, LocalDateTime orderTime, ArrayList<OrderItem> itemList) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderTime = orderTime;
        this.totalPrice = 0.0;
        this.itemList = itemList;
    }

    
    public void addItem(OrderItem item) {
        this.itemList.add(item);
    }

    // Getters
    public int getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public double getTotalPrice() { return totalPrice; }
    public ArrayList<OrderItem> getItemList() { return itemList; }
    public Customer getCustomer() { return customer; }
}