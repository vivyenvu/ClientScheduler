package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    private static PreparedStatement ps;
    private static ResultSet rs;

    //use for delete, insert, or update sql statements
    public static PreparedStatement getPS (String sql) throws SQLException {
        ps = DBConnection.getConnection().prepareStatement(sql);
        return ps;
    }

    //use for select sql statements
    public static ResultSet getRS(String sql) throws SQLException {
        rs = getPS(sql).executeQuery();
        return rs;
    }

    /*private static String query;
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
    }*/
}
