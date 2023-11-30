import java.sql.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    //All variables declaration====================================================================================================================================
    String Red = "\u001B[31m";
    String Black = "\u001B[30m";
    String Green = "\u001B[32m";
    String username;
    String password;
    int userID;
    Connection con = null;
    Statement st = null;

    //Login Main Logic====================================================================================================================================
    //public static void Login_main() throws SQLException {
    public static void main(String[] args) throws SQLException {
        Login ref = new Login();
        ref.createConnection();
        ref.breaker();
        System.out.println(ref.Green + "Welcome to Login Page!");
        ref.authenticateUser();
        ref.closeConnection();
    }

    //Login Breaker Logic====================================================================================================================================
    public void breaker() {
        System.out.println(Red + "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //JDBC Connection with MySQL Logic====================================================================================================================================
    public void createConnection() throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_book_store", "root", "XLZ5*YdZO991RUkm");
            st = con.createStatement();
            System.out.println(Green + "Database Connection Successful!");
        } catch (SQLException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost");
        }
    }

    //Closing Connection after Successful Login Logic====================================================================================================================================
    public void closeConnection() throws SQLException {
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Authentication of User====================================================================================================================================
    public void authenticateUser() {
        getUsername();
        breaker();
        getPassword();
        breaker();
        try {
            if (isUserValid(username, password)) {
                if ("Gautam@123".equals(username) && "Qwertyuiop@123".equals(password)) {
                    System.out.println(Green + "Welcome " + username + " to Admin Portal!");
                    /* ResultSet resultSet = null;
                    try {
                        String query = "Select userID from loginregister WHERE username = '" + username + "'";
                        System.out.println(Green + query);
                        resultSet = st.executeQuery(query);
                        if (resultSet.next()) {
                            int userID = resultSet.getInt("userID");
                            System.out.println("Allocated User ID is : " + userID);
                        } else {
                            System.out.println("UserID not found in the database.");
                        }
                    } catch(NullPointerException e){
                        System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
                    } */
                    //AdminPortal.Admin_main();
                } else {
                    System.out.println(Green + "Welcome  " + username + " to Customer Portal!");
                    ResultSet resultSet = null;
                    try {
                        String query = "Select userID from loginregister WHERE username = '" + username + "'";
                        System.out.println(Green + query);
                        resultSet = st.executeQuery(query);
                        if (resultSet.next()) {
                            userID = resultSet.getInt("userID");
                            System.out.println("Allocated User ID is : " + userID);
                        } else {
                            System.out.println("UserID not found in the database.");
                        }
                    } catch(NullPointerException e){
                        System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
                    }
                    //CustomerPortal.Customer_main();
                }
            } else {
                System.out.println(Red + "Username or Password doesn't exists in database! Please try again.");
                authenticateUser();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Validation of User====================================================================================================================================
    public boolean isUserValid(String username, String password) throws SQLException {
        String query = "SELECT * FROM loginregister WHERE BINARY username = '" + escapeString(username) + "' AND confirmPassword = '" + escapeString(password) + "'";
        try (ResultSet resultSet = st.executeQuery(query)) {
            return resultSet.next();
        } catch (NullPointerException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
        return false;
    }

    // Method to escape special characters in SQL strings====================================================================================================================================
    private String escapeString(String input) {
        return input.replace("'", "''");
    }

    //Login Username and Validation Logic====================================================================================================================================
    public void getUsername() {
        Scanner sc = new Scanner(System.in);
        System.out.print(Black + "Enter Username : ");
        username = sc.nextLine();
        if (validateCustomerUsername(username)) {
        } else {
            if (Objects.equals(username, "")) {
                System.out.println(Red + "Username cannot be empty!");
            } else {
                System.out.println(Red + "Invalid Username! It must at-least have one character, number and special character!");
            }
            System.out.print(Black + "Re-");
            getUsername();
        }
    }

    public boolean validateCustomerUsername(String username) {
        if (username == null) {
            return false;
        }
        String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_])(?=.*\\d).+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    //Login Password and Validation Logic====================================================================================================================================
    public void getPassword() {
        Scanner sc = new Scanner(System.in);
        boolean isValidPassword = false;
        do {
            System.out.print(Black + "Enter your Password : ");
            password = sc.nextLine();

            if (validationCustomerConfirmPassword(password)) {
                isValidPassword = true;
            } else {
                if (password.isEmpty()) {
                    System.out.println(Red + "Password cannot be empty!");
                } else {
                    System.out.println(Red + "Invalid Password! It must have at least one uppercase letter, one lowercase letter, one special character, one digit, and be at least 8 characters long.");
                }
                System.out.print(Black + "Re-");
            }
        } while (!isValidPassword);
    }

    public boolean validationCustomerConfirmPassword(String password) {
        if (password == null) {
            return false;
        }
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}