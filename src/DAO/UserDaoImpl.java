package DAO;

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

    }
}
