package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacts {
    private int contactID;
    private String contactName;
    private String email;
    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    public Contacts (int contactID, String contactName, String email){
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    public int getContactID(){
        return contactID;
    }

    public String getContactName(){
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public static ObservableList<Contacts> getAllContacts(){
        return allContacts;
    }

    public void setContactID(int id) {
        contactID = id;
    }

    public void setContactName(String name) {
        contactName = name;
    }

    public void setEmail(String emailAddress) {
        email = emailAddress;
    }

    public static void setAllContacts(ObservableList<Contacts> toSet){
        allContacts = toSet;
    }

    @Override
    public String toString(){
        return getContactName();
    }
}
