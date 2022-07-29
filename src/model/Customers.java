package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String country;
    private int divisionIDFK;
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    public Customers(int customerID, String customerName, String address, String postalCode, String phone, String country, int divisionIDFK) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.country = country;
        this.divisionIDFK = divisionIDFK;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public int getDivisionIDFK() {
        return divisionIDFK;
    }

    public ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }

    public void setCustomerID(int id) {
        customerID = id;
    }

    public void setCustomerName(String name) {
        customerName = name;
    }

    public void setAddress (String inputAddress){
        address = inputAddress;
    }

    public void setPostalCode(String pCode){
        postalCode = pCode;
    }

    public void setPhone(String number) {
        phone = number;
    }

    public void setCountry (String inputCountry){
        country = inputCountry;
    }

    public void setDivisionIDFK(int id){
        divisionIDFK = id;
    }

    public static void addToAllCustomers(Customers add) {
        allCustomers.add(add);
    }

    public static void setAllCustomers(ObservableList<Customers> toSet){
        allCustomers = toSet;
    }

    public static Customers lookupCustomer(int id){
        for (Customers c : allCustomers){
            if(c.getCustomerID() == id){
                return c;
            }
        }
        return null;
    }

    public static Appointments checkForAppts(int custID) {
        for (Appointments appt : Appointments.getAllAppts()){
            if (appt.getCustomerIDFK() == custID) {
                return appt;
            }
        }
        return null;
    }
    @Override
    public String toString(){
        return Integer.toString(getCustomerID());
    }
}
