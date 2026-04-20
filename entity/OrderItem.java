package entity;

public class OrderItem {

    private String itemname; // "Popcorn" or "Drink"
    private double price;
    private int quantity;
    private double subtotal; 

    public OrderItem(String itemname, double price, int quantity) {
        this.itemname = itemname;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = price * quantity;
    }
    public OrderItem(String itemname, double price, int quantity, double subtotal) {
        this.itemname = itemname;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal; 
    }
    // Getters and setters
    public String getItemname() {
        return itemname;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public double getSubtotal() {
        return subtotal;
    }
}
