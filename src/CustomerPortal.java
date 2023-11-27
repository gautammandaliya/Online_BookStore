import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

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
        ref.breaker();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
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
        CustomerPortal ref = new CustomerPortal();
        System.out.println(Green + "Displaying All Books Present in Database!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        try {
            String query = "Select * from bookdetails";
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(Black + "Book Series number : " + Green + resultSet.getInt(1));
                System.out.println(Black + "Book name : " + Green + resultSet.getString(2));
                System.out.println(Black + "Author name : " + Green + resultSet.getString(3));
                System.out.println(Black + "Quantity Left : " + Green + resultSet.getInt(4));
                System.out.println(Black + "Price per book : " + Green + resultSet.getDouble(5));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        } catch (NullPointerException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
        breaker();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
        ref.customerChoice();
    }

    //Search By Book name Option Logic====================================================================================================================================
    public void searchBookName() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        breaker();
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
        ref.customerChoice();
    }

    //Search By Author name Option Logic====================================================================================================================================
    public void searchAuthorName() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        breaker();
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
        ref.customerChoice();
    }

    //Cart Option Logic====================================================================================================================================
    public void cart() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        breaker();
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
        ref.customerChoice();
    }

    //Previous Orders Option Logic====================================================================================================================================
    public void previousOrders() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        breaker();
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
        ref.customerChoice();
    }

    //Sign-out Option Logic====================================================================================================================================
    public void signout() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        breaker();
        CustomerPortal ref = new CustomerPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
        ref.customerChoice();
    }

    //Delete Account Option Logic====================================================================================================================================
    public void deleteAccount() throws SQLException {
        Scanner sc = new Scanner(System.in);
        CustomerPortal ref = new CustomerPortal();
        String answer;
        System.out.print(Black + "Do you really want to Delete Account (Yes / No) : ");
        answer = sc.next();
        if(answer.equalsIgnoreCase("Yes"))
        {
            int userID;
            try {
                String query = "Select * from loginregister";
                ResultSet resultSet = st.executeQuery(query);
            } catch (NullPointerException e) {
                System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
            }
            try {
                userID = userID();
                if (isUserIDPresent(userID)) {
                    System.out.println(Green + "User with userID " + userID + " is present in the database!");
                    try {
                        String deleteQuery = "Delete from loginregister WHERE userID = " + userID;
                        int rowsAffected = st.executeUpdate(deleteQuery);
                        if(rowsAffected > 0){
                            System.out.println(Green + "Account Deleted Successfully!");
                            breaker();
                            System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
                            ref.customerChoice();
                        } else {
                            System.out.println(Red + "Error : No user record found!");
                            breaker();
                        }
                    } catch (NullPointerException e) {
                        System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
                    }
                } else {
                    System.out.println(Red + "User with userID " + userID + " is not present in the database!");
                    breaker();
                    System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
                    ref.customerChoice();
                }
            } catch (SQLException e) {
                System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
                e.printStackTrace();
            }
        } else {
            System.out.println(Green + "Account Archived!");
            breaker();
            System.out.println(ref.Black + "Select one Option :\n1. Display All Book\n2. Search by Book name\n3. Search by Author name\n4. Cart\n5. Previous Orders\n6. Sign-out\n7. Delete Account");
            ref.customerChoice();
        }
    }
    //Asking for userID for updating and deleting records and checking in database====================================================================================================================================
    public int userID() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int userID;
        try {
            System.out.print(Black + "Enter allocated userID to Delete Account: ");
            String input = sc.nextLine();
            userID = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println(Red + "Please enter number only!");
            return userID();
        }
        return userID;
    }
    public boolean isUserIDPresent(int userID) throws SQLException {
        ResultSet resultSet = null;
        try {
            String query = "Select * from loginregister WHERE userID = " + userID;
            System.out.println(Green + query);
            resultSet = st.executeQuery(query);
            return resultSet.next();
        } catch(NullPointerException e){
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
        return false;
    }
}