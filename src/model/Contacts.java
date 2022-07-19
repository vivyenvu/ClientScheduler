package model;

public class Contacts {
    private int contactID;
    private String contactName;
    private String email;

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

    public void setContactID(int id) {
        contactID = id;
    }

    public void setContactName(String name) {
        contactName = name;
    }

    public void setEmail(String emailAddress) {
        email = emailAddress;
    }
}
