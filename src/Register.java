import java.sql.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {

    //All variable declaration====================================================================================================================================
    String Red = "\u001B[31m";
    String Black = "\u001B[30m";
    String Green = "\u001B[32m";
    int customer_userID = 0;
    String customer_name;
    int customer_gender;
    String customer_address;
    long customer_mobileNumber;
    String customer_username;
    String customer_emailID;
    String customer_createPassword;
    String customer_confirmPassword;
    Connection con = null;
    Statement st = null;

    //Register Main Logic====================================================================================================================================
    //public static void Register_main() throws SQLException {
    public static void main(String[] args) throws SQLException {
        Register ref = new Register();
        ref.createConnection();
        int customer_userID = ref.retrieveUserID();
        System.out.println(ref.Black + "Auto-generated UserID is " + customer_userID);
        ref.createRecord();
        ref.closeConnection();
    }

    //Register Breaker Logic====================================================================================================================================
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

    //Creating Records to fit into database Logic====================================================================================================================================
    public void createRecord() throws SQLException {
        Register ref = new Register();
        ref.breaker();
        System.out.println(Green + "Welcome to Registration Page!");
        ref.customer_name();
        ref.breaker();
        ref.customer_gender();
        ref.breaker();
        ref.customer_address();
        ref.breaker();
        ref.customer_mobileNumber();
        ref.breaker();
        ref.customer_username();
        ref.breaker();
        ref.customer_emailID();
        ref.breaker();
        ref.customer_createPassword();
        ref.breaker();
        ref.customer_confirmPassword();
        ref.breaker();

        if (isUsernameExists(ref.customer_username)) {
            System.out.println(Red + "Username already Exist! Please enter a unique username!");
            ref.customer_username();
        }
        if (isEmailExists(ref.customer_emailID)) {
            System.out.println(Red + "Email ID already Exist! Please enter a unique email ID!");
            ref.customer_emailID();
        }

        try {
            String createRecordQuery = "INSERT INTO loginregister (name, gender, address, mobileNumber, username, emailID, createPassword, confirmPassword) VALUES ('" + ref.customer_name + "', " + ref.customer_gender + ", '" + ref.customer_address + "', " + ref.customer_mobileNumber + ", '" + ref.customer_username + "', '" + ref.customer_emailID + "', '" + ref.customer_createPassword + "', '" + ref.customer_confirmPassword + "')";
            System.out.println(Green + createRecordQuery);
            st.execute(createRecordQuery);
            System.out.println(Green + "Registration Record Captured!");
            //Login.Login_main();
        } catch (NullPointerException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
    }

    //Checking if Username exists in database or not logic====================================================================================================================================
    public boolean isUsernameExists(String username) throws SQLException {
        ResultSet resultSet = null;
        try {
            String query = "Select * from loginregister where username = '" + username + "'";
            resultSet = st.executeQuery(query);
        } catch (NullPointerException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
        return resultSet.next();
    }

    //Checking if EmailID exists in database or not logic====================================================================================================================================
    public boolean isEmailExists(String email) throws SQLException {
        ResultSet resultSet = null;
        try {
            String query = "Select * from loginregister where emailID = '" + email + "'";
            resultSet = st.executeQuery(query);
        } catch (NullPointerException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
        return resultSet.next();
    }

    //Retrieving userID from database====================================================================================================================================
    public int retrieveUserID() {
        try {
            String query = "Select max(userID) as userID from loginregister";
            ResultSet resultSet = st.executeQuery(query);
            if (resultSet.next()) {
                customer_userID = resultSet.getInt("userID") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
        return customer_userID;
    }

    //Closing Connection after Successful Creation Logic====================================================================================================================================
    public void closeConnection() throws SQLException {
        st.close();
        con.close();
    }

    //Register Name and Validation Logic====================================================================================================================================
    public void customer_name() {
        Scanner sc = new Scanner(System.in);
        System.out.print(Black + "Enter Name (Firstname Lastname) : ");
        customer_name = sc.nextLine();
        if (validateCustomerName(customer_name)) {
            System.out.println(Green + customer_name + " is a valid name!");
        } else {
            if (Objects.equals(customer_name, "")) {
                System.out.println(Red + "Name cannot be empty!");
            } else {
                System.out.println(Red + "Name Format (Firstname Lastname) without numbers!");
            }
            System.out.print(Black + "Re-");
            customer_name();
        }
    }

    public boolean validateCustomerName(String name) {
        String regex = "^[a-zA-Z\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches() && !name.matches(".*\\d.*") && name.split("\\s").length == 2;
    }

    //Register Gender Logic====================================================================================================================================
    public void customer_gender() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Black + "Gender Options : 1.Male 2.Female 3.Can't Disclose");
        try {
            System.out.print(Black + "Enter your Option : ");
            customer_gender = sc.nextInt();
            if (customer_gender == 1) {
                System.out.println(Green + "Male Selected!");
            } else if (customer_gender == 2) {
                System.out.println(Green + "Female Selected!");
            } else if (customer_gender == 3) {
                System.out.println(Green + "Can't Disclose Selected!");
            } else {
                System.out.println(Red + "Invalid Choice!");
                customer_gender();
            }
        } catch (InputMismatchException e) {
            System.out.println(Red + "Enter numbers only!");
            customer_gender();
        }
    }

    //Register Address Logic====================================================================================================================================
    public void customer_address() {
        Scanner sc = new Scanner(System.in);
        System.out.print(Black + "Enter your Address : ");
        customer_address = sc.nextLine();
        if (Objects.equals(customer_address, "")) {
            System.out.println(Red + "Address cannot be empty!");
            System.out.print(Black + "Re-");
            customer_address();
        } else {
            System.out.println(Green + customer_address + " is valid Address!");
        }
    }

    //Register Mobile Number and Validation Logic====================================================================================================================================
    public void customer_mobileNumber() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter Mobile Number : ");
            customer_mobileNumber = sc.nextLong();
            if (validateCustomerMobileNumber(customer_mobileNumber)) {
                System.out.println(Green + customer_mobileNumber + " is Correct!");
            } else {
                System.out.println(Red + "Mobile number should start with either 7, 8 or 9 and must be 10-digits long!");
                System.out.print(Black + "Re-");
                customer_mobileNumber();
            }
        } catch (InputMismatchException e) {
            System.out.println(Red + "Only Numbers are Allowed");
            customer_mobileNumber();
        }
    }

    public boolean validateCustomerMobileNumber(long mobileNumber) {
        String regex = "[789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(mobileNumber));
        return matcher.matches();
    }

    //Register Username and Validation Logic====================================================================================================================================
    public void customer_username() {
        Scanner sc = new Scanner(System.in);
        System.out.print(Black + "Enter Username : ");
        customer_username = sc.nextLine();
        if (validateCustomerUsername(customer_username)) {
            System.out.println(Green + customer_username + " is a valid Username!");
        } else {
            if (Objects.equals(customer_username, "")) {
                System.out.println(Red + "Username cannot be empty!");
            } else {
                System.out.println(Red + "Invalid Username! It must at-least have one character, number and special character!");
            }
            System.out.print(Black + "Re-");
            customer_username();
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

    //Register Email-Id and validation Logic====================================================================================================================================
    public void customer_emailID() {
        Scanner sc = new Scanner(System.in);
        System.out.print(Black + "Enter Valid Email-ID : ");
        customer_emailID = sc.nextLine();
        if (validateCustomerEmailID(customer_emailID)) {
            System.out.println(Green + customer_emailID + " is valid Email Address!");
        } else {
            System.out.println(Red + "Invalid EmailID! It must be in this manner - abc@mailservice.domain");
            System.out.print(Black + "Re-");
            customer_emailID();
        }
    }

    public boolean validateCustomerEmailID(String email) {
        try {
            if (email.isEmpty()) {
                System.out.print(Red + "Email Address cannot be empty! ");
                return false;
            }
            if (Character.isDigit(email.charAt(0)) || email.matches(".*[A-Z].*")) {
                System.out.print(Red + "Uppercase Letters and Starting Numeric are not allowed! ");
                return false;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(Red + "EmailID Cannot be empty!");
            System.out.print(Black + "Re-");
            return false;
        }
        String regex = "^[a-z][a-z0-9]*@[a-z]+\\.[a-z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Register Create Password and Validation Logic====================================================================================================================================
    public void customer_createPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Black + "Password should be 8-character long with\n1. One Uppercase-Character\n2. One Lowercase-Character\n3. One Special-Character\n4. One Digit");
        do {
            System.out.print(Black + "Create your Password : ");
            customer_createPassword = sc.nextLine();
            if (customer_createPassword.isEmpty()) {
                System.out.println(Red + "Create Password cannot be empty!");
            } else if (validationCustomerCreatePassword(customer_createPassword)) {
                System.out.println(Green + "Password is valid!");
                break;
            } else {
                System.out.println(Red + "Invalid Password! Try Again.");
            }
        } while (true);
    }

    public boolean validationCustomerCreatePassword(String createPassword) {
        if (createPassword == null) {
            return false;
        }
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(createPassword);
        return matcher.matches();
    }

    //Register Confirm Password and Validation Logic====================================================================================================================================
    public void customer_confirmPassword() throws SQLException {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(Black + "Confirm your Password : ");
            customer_confirmPassword = sc.nextLine();

            if (validationCustomerConfirmPassword(customer_confirmPassword)) {
                if (customer_confirmPassword.equals(customer_createPassword)) {
                    System.out.println(Green + "Registration Successfully Done!");
                    break;
                } else {
                    if (customer_confirmPassword.isEmpty()) {
                        System.out.println(Red + "Confirm Password cannot be empty!");
                    } else {
                        System.out.println(Red + "Create-Password and Confirm-Password do not match!");
                    }
                }
            } else {
                System.out.println(Red + "Invalid Password! Try Again.");
            }

            System.out.print(Black + "Re-");
        } while (true);
    }

    public boolean validationCustomerConfirmPassword(String confirmPassword) {
        if (confirmPassword == null) {
            return false;
        }
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(confirmPassword);
        return matcher.matches();
    }
}