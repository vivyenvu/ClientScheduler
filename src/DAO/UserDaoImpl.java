package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {
    public static Users getUser(String userName) throws SQLException, Exception {
        try{
            //PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            //ResultSet rs = ps.executeQuery();
            ResultSet rs = Query.getResult("SELECT * FROM users WHERE User_Name = ' " + userName + "'");

            while(rs.next()) {
                int userid = rs.getInt("User_ID_");
                String username = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users userResult = new Users(userid, username, password);

                //what happens if there's more than one user with this username?
                return userResult;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static ObservableList<Users> getAllUsers() throws SQLException, Exception{
        ObservableList<Users> allUsers = FXCollections.observableArrayList();

        ResultSet result = Query.getResult("select * from users");
        while(result.next()){
            int userid = result.getInt("User_ID");
            String userNameG = result.getString("User_Name");
            String password = result.getString("Password");
            Users userResult = new Users(userid, userNameG, password);
            allUsers.add(userResult);
        }
        return allUsers;
    }
}
