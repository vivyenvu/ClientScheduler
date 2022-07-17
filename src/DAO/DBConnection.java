package DAO;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    public static Connection connection;

    //connecting to database
    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = (Connection)DriverManager.getConnection(jdbUrl, userName, password);
            System.out.println("Connection successful");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //closing that connection
    public static void closeConnection() {
        try{
            connection.close();
            System.out.println("Connection closed");
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
    public static void makeConnection() throws ClassNotFoundException, SQLException, Exception {
        MysqlDataSource d = new MysqlDataSource();
        d.setUser(userName);
        d.setPassword(password);
        d.setUrl(jdbUrl);
        d.setDatabaseName(databaseName);
        connection = (Connection) d.getConnection();
        System.out.println("Connection Successful");

    }
}
