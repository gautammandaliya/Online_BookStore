import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //All Variables Declaration====================================================================================================================================
    String Red = "\u001B[31m";
    String Black = "\u001B[30m";
    String Green = "\u001B[32m";
    int customer_preference;

    //Main Method for starting project====================================================================================================================================
    public static void main(String[] args) throws SQLException {
        Main ref = new Main();
        ref.Customer_preference();
    }

    //Main Breaker Logic====================================================================================================================================
    public void breaker() {
        System.out.println(Red + "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void Customer_preference() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println(Black + "1. Existing User --> Login Here!");
        System.out.println(Black + "2. New User --> Register Here!");
        try {
            System.out.print(Black + "Enter your Choice : ");
            String input = sc.nextLine().trim();
            if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
                customer_preference = Integer.parseInt(input);
                if (customer_preference >= 1 && customer_preference <= 2) {
                    Main ref = new Main();
                    ref.breaker();
                    if (customer_preference == 1) {
                        //Login.Login_main();
                    } else {
                        //Register.Register_main();
                    }
                } else {
                    System.out.println(Red + "Please enter a valid choice!");
                    Customer_preference();
                }
            } else {
                System.out.println(Red + "Please enter a single digit!");
                Customer_preference();
            }
        } catch (InputMismatchException e) {
            System.out.println(Red + "Please enter number only!");
            Customer_preference();
        }
    }
}