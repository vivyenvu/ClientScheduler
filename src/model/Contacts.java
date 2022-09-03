package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for object type Contacts.
 */
public class Contacts {
    private int contactID;
    private String contactName;
    private String email;
    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /**
     * Constructor for Contacts object
     * @param contactID contact id
     * @param contactName contact name
     * @param email contact email
     */
    public Contacts (int contactID, String contactName, String email){
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Gets contact id
     * @return contact id
     */
    public int getContactID(){
        return contactID;
    }

    /**
     * Gets contact name
     * @return contact name
     */
    public String getContactName(){
        return contactName;
    }

    /**
     * Gets contact email
     * @return contact email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets list of all contacts
     * @return list of all contacts
     */
    public static ObservableList<Contacts> getAllContacts(){
        return allContacts;
    }

    /**
     * Sets contact id
     * @param id contact id
     */
    public void setContactID(int id) {
        contactID = id;
    }

    /**
     * Sets contact name
     * @param name name of contact
     */
    public void setContactName(String name) {
        contactName = name;
    }

    /**
     * Sets contact email address
     * @param emailAddress email address of contact
     */
    public void setEmail(String emailAddress) {
        email = emailAddress;
    }

    /**
     * Sets list to the list of allContacts
     * @param toSet new list to be assigned to allContacts
     */
    public static void setAllContacts(ObservableList<Contacts> toSet){
        allContacts = toSet;
    }

    /**
     * Provides string name of contact
     * @return name of contact
     */
    @Override
    public String toString(){
        return getContactName();
    }
}
