import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerPortal {
    //All variables declaration
    String Red = "\u001B[31m";
    String Black = "\u001B[30m";
    String Green = "\u001B[32m";
    Connection con = null;
    Statement st = null;

    //Customer Portal Main Logic====================================================================================================================================
    //public static void Customer_main() throws SQLException
    public static void main(String[] args) throws SQLException{
        CustomerPortal ref = new CustomerPortal();
        ref.createConnection();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Signout\n7. Delete Account");
        ref.customerChoice();
    }

    //Admin Portal Breaker Logic====================================================================================================================================
    public void breaker() {
        System.out.println(Red + "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //JDBC Connection with MySQL Logic====================================================================================================================================
    public void createConnection() throws SQLException{
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_book_store", "root", "XLZ5*YdZO991RUkm");
            st = con.createStatement();
            System.out.println(Green + "Database Connection Successful!");
        } catch (SQLException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost");
        }
    }

    //Closing Connection====================================================================================================================================
    public void closeConnection() throws SQLException {
        con.close();
        st.close();
    }

    //Option Choosing====================================================================================================================================
    public void customerChoice() throws SQLException {
        int choice;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter your Choice : ");
            String input = sc.nextLine().trim();
            if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 7) {
                    breaker();
                    if (choice == 1) {
                        allBooks();
                    } else if (choice == 2) {
                        searchBookName();
                    } else if (choice == 3) {
                        searchAuthorName();
                    } else if (choice == 4) {
                        cart();
                    } else if (choice == 5) {
                        previousOrders();
                    } else if (choice == 6) {
                        signout();
                    } else {
                        deleteAccount();
                    }
                } else {
                    System.out.println(Red + "Please enter a valid choice!");
                    customerChoice();
                }
            } else {
                System.out.println(Red + "Please enter a single digit!");
                customerChoice();
            }
        } catch (NumberFormatException e) {
            System.out.println(Red + "Please enter a valid choice!");
            customerChoice();
        }
    }

    //Display All Books Option Logic====================================================================================================================================
    public void allBooks() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Signout\n7. Delete Account");
        ref.customerChoice();
    }

    //Search By Book name Option Logic====================================================================================================================================
    public void searchBookName() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Signout\n7. Delete Account");
        ref.customerChoice();
    }

    //Search By Author name Option Logic====================================================================================================================================
    public void searchAuthorName() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Signout\n7. Delete Account");
        ref.customerChoice();
    }

    //Cart Option Logic====================================================================================================================================
    public void cart() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Signout\n7. Delete Account");
        ref.customerChoice();
    }

    //Previous Orders Option Logic====================================================================================================================================
    public void previousOrders() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Signout\n7. Delete Account");
        ref.customerChoice();
    }

    //Signout Option Logic====================================================================================================================================
    public void signout() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Signout\n7. Delete Account");
        ref.customerChoice();
    }

    //Delete Account Option Logic====================================================================================================================================
    public void deleteAccount() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Signout\n7. Delete Account");
        ref.customerChoice();
    }
}