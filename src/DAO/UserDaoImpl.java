package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interacts with Users information from the database.
 */
public class UserDaoImpl {

    /**
     * Gets User with associated username
     * @param inputUsername associated username
     * @return User with information pulled from database
     * @throws SQLException
     */
    public static Users getUser(String inputUsername) throws SQLException {
        try{
            ResultSet rs = Query.getRS("SELECT * FROM users WHERE User_Name = '" + inputUsername + "'");
            while(rs.next()) {
                int userid = rs.getInt("User_ID");
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

    /**
     * Gets a list of all users and their information from the database.
     * @return list of all users
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Users> getAllUsers() throws SQLException, Exception{
        ObservableList<Users> loadUsers = FXCollections.observableArrayList();

        ResultSet result = Query.getRS("select * from users");
        while(result.next()){
            int userid = result.getInt("User_ID");
            String userNameG = result.getString("User_Name");
            String password = result.getString("Password");
            Users userResult = new Users(userid, userNameG, password);
            loadUsers.add(userResult);
        }
        Users.setAllUsers(loadUsers);
        return loadUsers;
    }
}
