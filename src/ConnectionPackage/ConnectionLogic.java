package ConnectionPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionLogic {
    String Red = "\u001B[31m";
    String Black = "\u001B[30m";
    String Green = "\u001B[32m";
    Connection con = null;
    Statement st = null;
    //JDBC Connection with MySQL Logic====================================================================================================================================
    public void createConnection() {
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
}
