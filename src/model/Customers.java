package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionIDFK;
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList(); //maybe I don't need this?

    public Customers(int customerID, String customerName, String address, String postalCode, String phone, int divisionIDFK) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
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

    public int getDivisionIDFK() {
        return divisionIDFK;
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

    public void setDivisionIDFK(int id){
        divisionIDFK = id;
    }
}
