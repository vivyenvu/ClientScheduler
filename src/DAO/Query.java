package DAO;

import com.mysql.cj.xdevapi.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    public static void makeQuery(String q){
        query = q;
        try{
            stmt = (Statement) DBConnection.connection.createStatement();
            if(query.toLowerCase().startsWith("select"));
            result = stmt.executeQuery(q);
            if(query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") ||query.toLowerCase().startsWith("update")){
            stmt.executeUpdate(q);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static ResultSet getResult() {
        return result;
    }
}
