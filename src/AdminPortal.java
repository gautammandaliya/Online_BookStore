import java.sql.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class AdminPortal {
    //All variable declaration====================================================================================================================================
    String Red = "\u001B[31m";
    String Black = "\u001B[30m";
    String Green = "\u001B[32m";
    int series_number, quantity, total_book_sales, total_sales_amount, update_quantity;
    String book_name, book_author, update_book_name, update_book_author;
    double price, update_price;
    Connection con = null;
    Statement st = null;

    //Admin Portal Main Method Logic====================================================================================================================================
    //public static void Admin_main() throws SQLException {
    public static void main(String[] args) throws SQLException {
        AdminPortal ref = new AdminPortal();
        ref.createConnection();
        System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
        ref.adminChoice();
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
    public void adminChoice() throws SQLException {
        int choice;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter your Choice : ");
            String input = sc.nextLine().trim();
            if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 5) {
                    breaker();
                    if (choice == 1) {
                        addBooks();
                    } else if (choice == 2) {
                        editBooks();
                    } else if (choice == 3) {
                        totalBooks();
                    } else if (choice == 4) {
                        deleteBook();
                    } else {
                        logout();
                    }
                } else {
                    System.out.println(Red + "Please enter a valid choice!");
                    adminChoice();
                }
            } else {
                System.out.println(Red + "Please enter a single digit!");
                adminChoice();
            }
        } catch (NumberFormatException e) {
            System.out.println(Red + "Please enter a valid choice!");
            adminChoice();
        }
    }

    //Adding Books Option Logic====================================================================================================================================
    public void addBooks() throws SQLException {
        AdminPortal ref = new AdminPortal();
        System.out.println(Black + "Add New Book!");
        ref.series_number();
        ref.breaker();
        ref.book_name();
        ref.breaker();
        ref.book_author();
        ref.breaker();
        ref.quantity();
        ref.breaker();
        ref.price();
        ref.breaker();

        try {
            String createRecordQuery = "INSERT INTO bookdetails (series_no, book_name, book_author, quantity, price, total_book_sale, total_sales_amount)" +
                    "VALUES (" + ref.series_number + ", '" + ref.book_name + "', '" + ref.book_author + "', " + ref.quantity + ", " + ref.price + ", " + ref.total_book_sales + ", " + ref.total_sales_amount + ")";
            System.out.println(Green + createRecordQuery);
            st.execute(createRecordQuery);
            System.out.println(Green + "Book Added Successfully");
            System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
            adminChoice();
        } catch (NullPointerException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
            System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
            adminChoice();
        }
    }

    //Add Book Details Component====================================================================================================================================
    public void series_number() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter Book Series number : ");
            String input = sc.nextLine().trim();
            if (input.contains(" ")) {
                throw new InputMismatchException();
            }
            series_number = Integer.parseInt(input);
        } catch (InputMismatchException e) {
            System.out.println(Red + "Space not Allowed!");
            series_number();
        } catch (NumberFormatException e) {
            System.out.println(Red + "Invalid number format! Please enter a valid series number!");
            series_number();
        }
    }

    public void book_name() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter Book name : ");
            book_name = sc.nextLine().trim();
            if(isValidAlphabeticNumberInput(book_name)){
            } else if(Objects.equals(book_name, "")){
                System.out.println(Red + "Book name cannot be empty!");
                book_author();
            } else {
                System.out.println(Red + "Invalid Input! Only Alphabets and Numbers are allowed!");
                book_name();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Input Validation Method for Book name Logic====================================================================================================================================
    private boolean isValidAlphabeticNumberInput(String input){
        return input.matches("[a-zA-Z0-9 ]+");
    }

    public void book_author() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter Author name : ");
            book_author = sc.nextLine();
            if(isValidAlpabeticAuthorInput(book_author)){
            } else if(Objects.equals(book_author, "")){
                System.out.println(Red + "Author name cannot be empty!");
                book_author();
            } else {
                System.out.println(Red + "Invalid Input! Only Alphabets are allowed!");
                book_author();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Input Validation Method for Author name Logic====================================================================================================================================
    private boolean isValidAlpabeticAuthorInput(String input){
        return input.matches("[a-zA-Z ]+");
    }

    public void quantity() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Available Book Quantity : ");
            String input = sc.nextLine().trim();
            if (input.contains(" ")) {
                throw new InputMismatchException();
            }
            quantity = Integer.parseInt(input);
        } catch (InputMismatchException e) {
            System.out.println(Red + "Space not Allowed!");
            quantity();
        } catch (NumberFormatException e) {
            System.out.println(Red + "Invalid number format! Please enter a valid quantity!");
            quantity();
        }
    }

    public void price() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter Price : ");
            String input = sc.nextLine().trim();
            if (isValidPrice(input)) {
                price = Double.parseDouble(input);
            } else {
                System.out.println(Red + "Invalid input! Please enter a valid number or decimal value.");
                price();
            }
        } catch (InputMismatchException e) {
            System.out.println(Red + "Invalid Price!");
            price();
        }
    }
    private boolean isValidPrice(String input){
        return input.matches("\\d+(\\.\\d+)?");
    }

    //Editing Book Option Logic====================================================================================================================================
    public void editBooks() throws SQLException {
        AdminPortal ref = new AdminPortal();
        ref.retrieveBook();
        ref.breaker();
    }

    //Retrieving all Books from database and display option to edit====================================================================================================================================
    public void retrieveBook() throws SQLException {
        AdminPortal ref = new AdminPortal();
        createConnection();
        breaker();
        int seriesNumber;
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
        try {
            seriesNumber = userSeriesNumber();
            if (isSeriesNumberPresent(seriesNumber)) {
                System.out.println(Green + "Book with series number " + seriesNumber + " is present in the database!");
                try {
                    String query = "Select * from bookdetails WHERE series_no = " + seriesNumber;
                    ResultSet resultSet = st.executeQuery(query);
                    breaker();
                    while (resultSet.next()) {
                        System.out.println(Green + "Existing Book Details");
                        System.out.println(Black + "Book Series number : " + Green + resultSet.getInt(1));
                        System.out.println(Black + "Book name : " + Green + resultSet.getString(2));
                        System.out.println(Black + "Author name : " + Green + resultSet.getString(3));
                        System.out.println(Black + "Quantity Left : " + Green + resultSet.getInt(4));
                        System.out.println(Black + "Price per book : " + Green + resultSet.getDouble(5));
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Edit Book Details");
                        ref.update_book_name();
                        ref.update_book_author();
                        ref.update_quantity();
                        ref.update_price();
                        try {
                            String updateRecordQuery1 = "Update bookdetails set book_name = '" + ref.update_book_name + "' where series_no = " + seriesNumber;
                            System.out.println(Green + updateRecordQuery1);
                            String updateRecordQuery2 = "Update bookdetails set book_author = '" + ref.update_book_author + "' where series_no = " + seriesNumber;
                            System.out.println(Green + updateRecordQuery2);
                            String updateRecordQuery3 = "Update bookdetails set quantity = " + ref.update_quantity + " where series_no = " + seriesNumber;
                            System.out.println(Green + updateRecordQuery3);
                            String updateRecordQuery4 = "Update bookdetails set price = " + ref.update_price + " where series_no = " + seriesNumber;
                            System.out.println(Green + updateRecordQuery4);
                            st.execute(updateRecordQuery1);
                            st.execute(updateRecordQuery2);
                            st.execute(updateRecordQuery3);
                            st.execute(updateRecordQuery4);
                            breaker();
                            System.out.println(Green + "Book Updated Successfully");
                            System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
                            adminChoice();
                        } catch (NullPointerException e) {
                            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
                }
            } else {
                System.out.println(Red + "Book with series number " + seriesNumber + " is not present in the database!");
                breaker();
                System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
                adminChoice();
            }
        } catch (SQLException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
            e.printStackTrace();
        }
    }

    //Asking for series number for updating and deleting records and checking in database====================================================================================================================================
    public int userSeriesNumber() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int series_number;
        try {
            System.out.print(Black + "Enter Book series number : ");
            series_number = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(Red + "Please enter number only!");
            sc.nextLine();
            return userSeriesNumber();
        }
        return series_number;
    }

    public boolean isSeriesNumberPresent(int series_number) throws SQLException {
        ResultSet resultSet = null;
        try {
            String query = "Select * from bookdetails WHERE series_no = " + series_number;
            System.out.println(Green + query);
            resultSet = st.executeQuery(query);
            return resultSet.next();
        } catch(NullPointerException e){
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
        return false;
    }

    //Record Updating Logic====================================================================================================================================
    public void update_book_name() throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter New Book name : ");
            update_book_name = sc.nextLine();
            if(isValidAlphabeticNumberInput(update_book_name)){
            } else if (Objects.equals(update_book_name, "")) {
                System.out.println(Red + "Book name cannot be empty!");
                update_book_name();
            } else {
                System.out.println(Red + "Invalid Input! Only Alphabets and Numbers are allowed!");
                update_book_name();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_book_author() throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter New Author name : ");
            update_book_author = sc.nextLine();
            if(isValidAlpabeticAuthorInput(update_book_author)){
            } else if (Objects.equals(update_book_author, "")) {
                System.out.println(Red + "Author name cannot be empty!");
                update_book_author();
            } else {
                System.out.println(Red + "Invalid Input! Only Alphabets are allowed!");
                update_book_author();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_quantity() throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "New Available Book Quantity : ");
            String input = sc.nextLine().trim();
            if (input.contains(" ")) {
                throw new InputMismatchException();
            }
            update_quantity = Integer.parseInt(input);
        } catch (InputMismatchException e) {
            System.out.println(Red + "Space not Allowed!");
            update_quantity();
        } catch (NumberFormatException e) {
            System.out.println(Red + "Invalid number format! Please enter a valid quantity!");
            update_quantity();
        }
    }

    public void update_price() throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(Black + "Enter New Price : ");
            String input = sc.nextLine().trim();
            if (isValidPrice(input)) {
                update_price = Double.parseDouble(input);
            } else {
                System.out.println(Red + "Invalid input! Please enter a valid number or decimal value.");
                update_price();
            }
        } catch (InputMismatchException e) {
            System.out.println(Red + "Invalid Price!");
            update_price();
        }
    }

    //Total Book Details Logic====================================================================================================================================
    public void totalBooks() throws SQLException {
        System.out.println(Red + "Error 404 : Page Not Found");
        AdminPortal ref = new AdminPortal();
        System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
        adminChoice();
    }

    //Delete Book Logic====================================================================================================================================
    public void deleteBook() throws SQLException {
        Scanner sc = new Scanner(System.in);
        AdminPortal ref = new AdminPortal();
        createConnection();
        breaker();
        int seriesNumber;
        String answer;
        System.out.println(Green + "Displaying All Books Present in Database!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        try {
            String query = "Select * from bookdetails";
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(Black + "Book Series number : " + Green + resultSet.getInt(1));
                System.out.println(Black + "Book name : " + Green + resultSet.getString(2));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        } catch (NullPointerException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
        }
        try {
            seriesNumber = userSeriesNumber();
            if (isSeriesNumberPresent(seriesNumber)) {
                System.out.println(Green + "Book with series number " + seriesNumber + " is present in the database!");
                do {
                    System.out.print(Black + "Do you really want to Delete Book (Yes / No) : ");
                    answer = sc.next().trim();
                    if (!isValidAlpabeticInput(answer)) {
                        System.out.println(Red + "Invalid Input! Only Alphabets are Allowed!");
                    }
                } while (!isValidAlpabeticInput(answer));
                if(answer.equalsIgnoreCase("Yes"))
                {
                    try {
                        String deleteQuery = "Delete from bookdetails WHERE series_no = " + seriesNumber;
                        int rowsAffected = st.executeUpdate(deleteQuery);
                        if(rowsAffected > 0){
                            System.out.println(Green + "Book Deleted Successfully!");
                            breaker();
                            System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
                            adminChoice();
                        } else {
                            System.out.println(Red + "Error : No book record found!");
                            breaker();
                        }
                    } catch (NullPointerException e) {
                        System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
                    }
                } else {
                    System.out.println(Green + "Book Archived!");
                    breaker();
                    System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
                    adminChoice();
                }
            } else {
                System.out.println(Red + "Book with series number " + seriesNumber + " is not present in the database!");
                breaker();
                System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
                adminChoice();
            }
        } catch (SQLException e) {
            System.out.println(Red + "Please connect to MySQL Server Localhost and proceed further...");
            e.printStackTrace();
        }
    }

    //Logout method and Back to Login page Logic====================================================================================================================================
    public void logout() throws SQLException {
        Scanner sc = new Scanner(System.in);
        AdminPortal ref = new AdminPortal();
        String answer;
        do {
            System.out.print(Black + "Do you really want to Logout (Yes / No) : ");
            answer = sc.next().trim();
            if (!isValidAlpabeticInput(answer)) {
                System.out.println(Red + "Invalid Input! Only Alphabets are Allowed!");
            }
        } while (!isValidAlpabeticInput(answer));
        if (answer.equalsIgnoreCase("Yes")){
            System.out.println(Green + "Successfully Navigated to Login Page!");
            //Login.Login_main();
            breaker();
        } else {
            System.out.println(ref.Black + "Select one Option :\n1. Add New Book\n2. Edit Book Details\n3. Total Book Details\n4. Delete Book\n5. Logout");
            adminChoice();
        }
    }

    //Input Validation Method for Logout and Delete Book Logic====================================================================================================================================
    private boolean isValidAlpabeticInput(String input){
        return input.matches("[a-zA-Z]+");
    }
}