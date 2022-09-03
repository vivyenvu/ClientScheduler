package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for object type Customers.
 */
public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String country;
    private int divisionIDFK;
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /**
     * Constructor for Customers object.
     * @param customerID customer id
     * @param customerName customer name
     * @param address customer address
     * @param postalCode customer postal code
     * @param phone customer phone number
     * @param country customer's country name
     * @param divisionIDFK customer's first level division
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone, String country, int divisionIDFK) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.country = country;
        this.divisionIDFK = divisionIDFK;
    }

    /**
     * Gets customer id
     * @return customer id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Gets customer name
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Gets customer address
     * @return customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets customer postal code
     * @return customer postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets customer phone number
     * @return customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets customer country name
     * @return country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets customer first level division id
     * @return first level division id
     */
    public int getDivisionIDFK() {
        return divisionIDFK;
    }

    /**
     * Gets list of all customers
     * @return list of allCustomers
     */
    public static ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }

    /**
     * Sets customer id
     * @param id customer id
     */
    public void setCustomerID(int id) {
        customerID = id;
    }

    /**
     * Sets customer name
     * @param name customer name
     */
    public void setCustomerName(String name) {
        customerName = name;
    }

    /**
     * Sets customer address
     * @param inputAddress customer address
     */
    public void setAddress (String inputAddress){
        address = inputAddress;
    }

    /**
     * Sets customer postal code
     * @param pCode customer postal code
     */
    public void setPostalCode(String pCode){
        postalCode = pCode;
    }

    /**
     * Sets customer phone number
     * @param number customer phone number
     */
    public void setPhone(String number) {
        phone = number;
    }

    /**
     * Sets customer country name
     * @param inputCountry country name
     */
    public void setCountry (String inputCountry){
        country = inputCountry;
    }

    /**
     * Sets customer first level division id
     * @param id first level division id
     */
    public void setDivisionIDFK(int id){
        divisionIDFK = id;
    }

    /**
     * Sets list to be list of all customers
     * @param toSet list to be assigned to allCustomers
     */
    public static void setAllCustomers(ObservableList<Customers> toSet){
        allCustomers = toSet;
    }

    /**
     * Checks if a customer as associated appointments
     * @param custID customer id
     * @return Appointment object that's associated with customer id. Returns null otherwise.
     */
    public static Appointments checkForAppts(int custID) {
        for (Appointments appt : Appointments.getAllAppts()){
            if (appt.getCustomerIDFK() == custID) {
                return appt;
            }
        }
        return null;
    }

    /**
     * Provides customer id
     * @return customer id
     */
    @Override
    public String toString(){
        return Integer.toString(getCustomerID());
    }
}
