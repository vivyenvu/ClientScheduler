package DAO;

import java.sql.SQLException;

public class UserDaoImpl {
    //example from first DAO webinar
    public static User getUser(String userName) throws SQLException, Exception {
        DBConnection.openConnection();
        String sqlStatement = "select * FROM users WHERE User_Name = ' " + userName + "'";

        Query.makeQuery(sqlStatement);
        User userResult;
        ResultSet result= Query.getResult();
        while(result.next()) {
            int userid = result.getInt("User_ID_");
            String userNameG = result.getString("User_Name");
            String password = result.getString("Password");
            userResult = new User(userid, userName, password);
            return userResult;
            }
        DBConnection.closeConnection();
        return null;
    }
    public static ObservableList<User> getAllusers() throws SQLException, Exception{
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        DBConnection.openConnection();
        String sqlStatement = "select * from users";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while(result.next()){
            int userid = result.getInt("User_ID");
            String userNameG = result.getString("User_Name");
            String password = result.getString("Password");
            User userResult = new User(userid, userNameG, password);
            allUsers.add(UserResult);
        }
        DBConnection.closeConnection();
        return allUsers;
    }
}
