package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Helper class that shorthands the creation of PreparedStatements and ResultSets.
 */
public class Query {
    private static PreparedStatement ps;
    private static ResultSet rs;

    /**
     * Gets PreparedStatement to be used for delete, insert, or update sql statements.
     * @param sql String to be queried in the MySQL database
     * @return prepared statement
     * @throws SQLException
     */
    public static PreparedStatement getPS (String sql) throws SQLException {
        ps = DBConnection.getConnection().prepareStatement(sql);
        return ps;
    }

    /**
     * Gets ResultSet to be used for select sql statements.
     * @param sql String to be queried in the MySQL database
     * @return result set
     * @throws SQLException
     */
    public static ResultSet getRS(String sql) throws SQLException {
        rs = getPS(sql).executeQuery();
        return rs;
    }
}
