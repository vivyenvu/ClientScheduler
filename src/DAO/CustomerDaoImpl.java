package DAO;

import helper.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Pulls Customers information from the database.
 */
public class CustomerDaoImpl {

    /**
     * Populates a list of all customers and their information from the database.
     * @return list of all customers
     * @throws SQLException
     */
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        ObservableList<Customers> loadCustomers = FXCollections.observableArrayList();
        ResultSet result = Query.getRS("select * from customers " +
                                            "INNER JOIN first_level_divisions " +
                                            "ON customers.Division_ID = first_level_divisions.Division_ID " +
                                            "INNER JOIN countries " +
                                            "ON first_level_divisions.Country_ID = countries.Country_ID");
        while(result.next()){
            int custID = result.getInt("Customer_ID");
            String custName = result.getString("Customer_Name");
            String custAddress = result.getString("Address");
            String custPostal = result.getString("Postal_Code");
            String custPhone = result.getString("Phone");
            int custDivID = result.getInt("Division_ID");
            String custCountry = result.getString("Country");
            Customers custResult = new Customers(custID, custName, custAddress, custPostal, custPhone, custCountry, custDivID);
            loadCustomers.add(custResult);
        }
        Customers.setAllCustomers(loadCustomers);
        return loadCustomers;
    }

    /**
     * Adds a new customer to the database table called customers.
     * @param custName customer name
     * @param custAddress customer address
     * @param custPostal customer postal code
     * @param custPhone customer phone number
     * @param custDivID customer first level division id
     * @throws SQLException
     */
    public static void addCustomer(String custName, String custAddress, String custPostal, String custPhone, int custDivID) throws SQLException {
        int custID=0; //might not be a good initialization
        String sql = "INSERT INTO customers VALUES (?,?,?,?,?,NULL, NULL, NULL, NULL,?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ps.getGeneratedKeys();
        while(rs.next()) {
            custID = rs.getInt(1);
        }
        ps.setInt(1, custID);
        ps.setString (2, custName);
        ps.setString(3, custAddress);
        ps.setString(4, custPostal);
        ps.setString(5, custPhone);
        ps.setInt(6, custDivID);
        ps.execute();
    }

    /**
     * Deletes an existing extry from the customers table in the database.
     * @param id customer id
     * @throws SQLException
     */
    public static void deleteCustomer(int id) throws SQLException {
    //delete from appointments THEN delete from customer table
        try{
            PreparedStatement psa = Query.getPS("DELETE FROM appointments WHERE Customer_ID = ?");
            psa.setInt(1,id);
            psa.execute();

            PreparedStatement psc = Query.getPS("DELETE FROM customers WHERE Customer_ID = ?");
            psc.setInt(1, id);
            psc.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing entry in the customers table of the database.
     * @param customerID customer id
     * @param customerName customer name
     * @param address customer address
     * @param postalCode customer postal code
     * @param phone customer phone number
     * @param divisionIDFK customer first level division id
     */
    public static void updateCustomer(int customerID, String customerName, String address, String postalCode, String phone, int divisionIDFK){
        try{
            PreparedStatement ps = Query.getPS("UPDATE customers set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?");
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionIDFK);
            ps.setInt(6, customerID);
            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
