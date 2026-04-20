package CLI;
import java.util.Scanner;
import entity.User;
import action.UserAction;
public abstract class Menu {
    protected Scanner input = new Scanner(System.in);
    protected User user;
    protected UserAction userAction;
    public Menu(User user , UserAction action) {
        this.user = user;
        this.userAction = action;
    }
    public abstract void runMenu();

    protected void profileSettings(User user) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Profile Settings ===");
            System.out.println("1. Change Name");
            System.out.println("2. Change PIN");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1":

                    System.out.print("Enter new name: ");
                    String newName = input.nextLine().trim();
                    boolean Success = userAction.updateUserName(user, newName);
                    if (Success) {
                        System.out.println("Name updated successfully.");
                    } else {
                        System.out.println("Failed to update name.");
                    }
                    break;

                case "2":           
                    System.out.print("Enter new PIN: ");
                    String newPin = input.nextLine().trim();
                    boolean pinSuccess = userAction.updateUserPin(user, newPin);
                    if (pinSuccess) {
                        System.out.println("PIN updated successfully.");
                    } else {
                        System.out.println("Failed to update PIN.");
                    }
                    break;

                case "0":

                    running = false;
                    break;

                default:

                    System.out.println("Invalid Option");
                    break;

            }
        }
    }
}
