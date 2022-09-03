package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Countries;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pulls Contacts information from the database.
 */
public class ContactDaoImpl {

    /**
     * Populates a list of all contacts and their information from the database.
     * @return list of all contacts
     * @throws SQLException
     */
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        ResultSet result = Query.getRS("select * from contacts");
        while(result.next()){
            int contactID = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String email = result.getString("Email");
            Contacts contactResult = new Contacts (contactID, contactName, email);
            allContacts.add(contactResult);
        }
        return allContacts;
    }
}
