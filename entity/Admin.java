package entity;

public class Admin extends User {
    //constructor
    public Admin(int userId,String userName, String phoneNumber, String sixDigitPin) {
        super(userId,userName, phoneNumber, sixDigitPin);

    }
    //Overide getter
    @Override
    public String getDisplayId(){
            String formatId = String.format("ADM%03d", userId);
            return formatId;
    }
    public String toString() {
        return super.toString() + String.format("Role: Admin");
        }
}
