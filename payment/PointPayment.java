package payment;
public class PointPayment implements Payment {
    private int loyaltyPoints;

    public PointPayment() {
    }

    public PointPayment(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public boolean pay(double amount) {
        int requiredPoints = (int)(amount * 100);

        if (loyaltyPoints >= requiredPoints) {
            loyaltyPoints -= requiredPoints;
            return true;
        }

        return false;
    }

    @Override
    public String getPayMethodName() {
        return "Point Payment";
    }
}