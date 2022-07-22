package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl {
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        ResultSet result = Query.getRS("select * from customers");
        while(result.next()){
            int custID = result.getInt("Customer_ID");
            String custName = result.getString("Customer_Name");
            String custAddress = result.getString("Address");
            String custPostal = result.getString("Postal_Code");
            String custPhone = result.getString("Phone");
            int custDivID = result.getInt("Division_ID");
            Customers custResult = new Customers(custID, custName, custAddress, custPostal, custPhone, custDivID);
            allCustomers.add(custResult);
        }
        return allCustomers;
    }

    //public static void addCustomer()
}
