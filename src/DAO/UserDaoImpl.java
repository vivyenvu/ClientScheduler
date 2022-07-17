package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {
    //example from first DAO webinar
    public static Users getUser(String userName) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM users WHERE User_Name = ' " + userName + "'";

        Query.makeQuery(sqlStatement);
        Users userResult;
        ResultSet result= Query.getResult();
        while(result.next()) {
            int userid = result.getInt("User_ID_");
            String userNameG = result.getString("User_Name");
            String password = result.getString("Password");
            userResult = new Users(userid, userName, password);
            return userResult;
            }
        DBConnection.closeConnection();
        return null;
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
