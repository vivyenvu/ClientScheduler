package DAO;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helper class that establishes Java DataBase Connection(JDBC) so our program can interact with MySQL database.
 */
public class DBConnection {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    //helps with timezone conversion
    private static final String jdbUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection conn = null;

    /**
     * Opens JDBC.
     * @return Connection that allows application to aceess MySQL database
     */
    public static Connection openConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(jdbUrl, userName, password);
            System.out.println("Connection successful");
        }
        catch (SQLException | ClassNotFoundException e) {
            //System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Closes JDBC.
     */
    public static void closeConnection() {
        try{
            conn.close();
            System.out.println("Connection closed");
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Gets Connection that allows for JDBC.
     * @return Connection that allows for JDBC.
     */
    public static Connection getConnection() {
        return conn;
    }
}
