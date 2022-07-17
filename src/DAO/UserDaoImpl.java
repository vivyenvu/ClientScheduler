package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {
    public static Users getUser(String userName) throws SQLException, Exception {
        Users userResult;
        try{
            String sql = "SELECT * FROM users WHERE User_Name = ' " + userName + "'";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userid = rs.getInt("User_ID_");
                String username = rs.getString("User_Name");
                String password = rs.getString("Password");
                userResult = new Users(userid, username, password);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return userResult;
    }
    public static ObservableList<Users> getAllUsers() throws SQLException, Exception{
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from users";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while(result.next()){
            int userid = result.getInt("User_ID");
            String userNameG = result.getString("User_Name");
            String password = result.getString("Password");
            Users userResult = new Users(userid, userNameG, password);
            allUsers.add(userResult);
        }
        DBConnection.closeConnection();
        return allUsers;
    }
}
