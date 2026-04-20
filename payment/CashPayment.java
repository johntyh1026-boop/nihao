package payment;
public class CashPayment implements Payment {
    private double cashGiven;

    public CashPayment() {
    }

    public CashPayment(double cashGiven) {
        this.cashGiven = cashGiven;
    }

    public double getCashGiven() {
        return cashGiven;
    }

    public void setCashGiven(double cashGiven) {
        this.cashGiven = cashGiven;
    }

    @Override
    public boolean pay(double amount) {
        return cashGiven >= amount;
    }

    @Override
    public String getPayMethodName() {
        return "Cash Payment";
    }
}