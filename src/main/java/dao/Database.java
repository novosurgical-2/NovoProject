package dao;
 
import java.sql.Connection;
import java.sql.DriverManager;

// this class is utility used by other classes to manipulate or access mysql tables
public class Database {
 
	// this is a typical method to get a connection to the mySQL server. returns a connection object
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/novo",
                    "admin", "iman");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
 
    // utility method to close connection
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}