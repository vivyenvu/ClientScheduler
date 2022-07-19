package model;

public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionIDFK;

    public Customers(int customerID, String customerName, String address, String postalCode, String phone, int divisionIDFK) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionIDFK = divisionIDFK;
    }
}
