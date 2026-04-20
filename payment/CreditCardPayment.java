package payment;
public class CreditCardPayment implements Payment {
    private String cardNum;
    private String expDate;
    private String cvv;

    public CreditCardPayment() {
    }

    public CreditCardPayment(String cardNum, String expDate, String cvv) {
        this.cardNum = cardNum;
        this.expDate = expDate;
        this.cvv = cvv;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {

        if (cardNum == null || !cardNum.matches("\\d{16}")) {
            System.out.println("Payment Error: Invalid Card Number.");
            return false;
        }

        if (cvv == null || !cvv.matches("\\d{3}")) {
            System.out.println("Payment Error: Invalid CVV.");
            return false;
        }

        if (expDate == null || !expDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            System.out.println("Payment Error: Invalid Expiry Date.");
            return false;
        }

        System.out.println("Successfully deducted RM " + amount + " from card ending in " + cardNum.substring(12));
        return true;
    }

    @Override
    public String getPayMethodName() {
        return "Credit Card Payment";
    }
}
