package DAO;


//RETIRE THIS PAGE
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    public static void makeQuery(String q){
        query = q;
        try{
            stmt = DBConnection.getConnection().createStatement();
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