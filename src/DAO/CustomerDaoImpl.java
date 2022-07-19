package DAO;

import javafx.collections.ObservableList;
import model.Customers;
import model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl {
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        ResultSet result = Query.getRS("select * from users");
        while(result.next()){
            int userid = result.getInt("User_ID");
            String userNameG = result.getString("User_Name");
            String password = result.getString("Password");
            Users userResult = new Users(userid, userNameG, password);
            allCustomers.add(userResult);
        }
        return allUsers;
    }
}
