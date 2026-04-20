package tools;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class Tool {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //pin
    public static boolean isvalidPin(String pin){
        boolean valid = true;
        char[] c = pin.toCharArray();
        if(c.length < 6){
            return !valid;
        }
        for(int i = 0; i< c.length;i++){
            if (!Character.isDigit(c[i])) {
                return !valid;
            }
        }
        return valid;
    }
    //checking is vaildphone num or not
    public static boolean isvalidphone(String phoneNum){
        boolean valid = true;
        char[] c = phoneNum.toCharArray();
        if(c.length <10){
            return !valid;
        }
        else if(c[0] != '0'){
            return !valid;
        }
        
        for(int i = 0; i< c.length;i++){
            if (!Character.isDigit(c[i])) {
                return !valid;
            }
        }
        return valid;
    }
    public static boolean hasNum(String prompt){
        for (char c : prompt.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isNum(String prompt){
        if (prompt == null || prompt.trim().isEmpty()) {
            return false;
        }
        
        try {
            Integer.parseInt(prompt.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static int getValidInt(Scanner input, String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String str = input.nextLine().trim();
            if (isNum(str) && !str.isEmpty()) {
                return Integer.parseInt(str);
            }
            System.out.println("Error: Input digit only!!!");
        }
    }

    public static double getValidDouble(Scanner input, String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String str = input.nextLine().trim();
            if (isNum(str) && !str.isEmpty()) {
                return Double.parseDouble(str);
            }
            System.out.println("Error: Input a valid number!!!");
        }
    }

    public static int getValidInt(String str) {
        if (isNum(str) && !str.isEmpty()) {
            return Integer.parseInt(str);
        }
        return -1; // Return -1 to indicate invalid input
    }
    public static LocalDateTime readFutureTime(Scanner sc, String prompt) {
        LocalDateTime validTime = null;
        
        while (validTime == null) {
            System.out.print(prompt);
            String inputStr = sc.nextLine().trim();
            
            try {
                validTime = LocalDateTime.parse(inputStr, TIME_FORMATTER);
                
                if (validTime.isBefore(LocalDateTime.now())) {
                    System.out.println("Time must be in the future. Please try again.");
                    validTime = null; 
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use 'yyyy-MM-dd HH:mm'.");
            }
        }
        return validTime;
    }
}
