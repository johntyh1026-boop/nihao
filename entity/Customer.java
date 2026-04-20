package entity;
public class Customer extends User {
    //data field
    private int loyaltyPoints;

    //constructor
    public Customer(int userId,String name,String phoneNum, String sixDigitPin, int loyaltyPoints){
        super(userId,name,phoneNum,sixDigitPin);
        this.loyaltyPoints = loyaltyPoints;
    }
    //getter
    
    public String getDisplayId(){
        String formatId = String.format("CUST%03d", userId);
        return formatId;
    }
    public int getLoyaPoints(){
        return this.loyaltyPoints;
    }

    public void addpoint(int points){
        this.loyaltyPoints += points;
    }

    public void deductCustomerPoints(int points){
        this.loyaltyPoints -= points;
    }

    public String toString(){
        return super.toString() + String.format("Loyalty Points: %d\nRole: Customer", loyaltyPoints);
    }
}
