package entity;

public class User {
    protected int userId;
    protected String userName;
    protected String phoneNumber;
    protected String sixDigitPin;

    public User(int userId, String userName, String phoneNumber, String sixDigitPin) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.sixDigitPin = sixDigitPin;
    }

    // --- Getter ---
    public int getId() {
        return userId;
    }
    public String getDisplayId() {
        return String.format("USR%03d", userId);
    }

    public String getName() {
        return this.userName;
    } 

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSixDigitPin() {
        return sixDigitPin;
    }

    // --- Setter ---
    public void setName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSixDigitPin(String sixDigitPin) {
        this.sixDigitPin = sixDigitPin;
    }

    public String toString() {
        return String.format("User: %s\nName: %s\nPhone: %s\n", 
                getId(), userName, phoneNumber);
    }
}