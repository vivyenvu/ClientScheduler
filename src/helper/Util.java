package helper;

import DAO.Query;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
    public static void stringToAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    public int contactNameToID (String name) throws SQLException {
        int contactID = Integer.parseInt(null);
        ResultSet rs = Query.getRS("SELECT Contact_ID FROM contacts WHERE Contact_Name = '" +name +"'");
        while (rs.next()){
            contactID = rs.getInt("Contact_ID");
        }
        stringToAlert("ContactID is " +contactID); //remove after testing
        return contactID;
    }

    public String contactIDToName(int id) {
        String name =""; //remove initialization later
        //search database for id and associated name
        return name;
    }

    public int usernameToID (String name) {
        int id = 0;// remove later
        //database for username and id
        return id;
    }

    public String userIDToName (int id) {
        String name = ""; //remove later
        //user ID to Name
        return name;
    }

    public int firstDivToID (String name) {
        int id = 0; //remove initialization
        //First division to ID
        return id;
    }

    public String firstIDtoDiv (int id) {
        String name = ""; //remove initialization
        //first division id to first division name
        return name;
    }

    //function to convert System time to UTC
    //function to convert UTC to System time
    //function to convert UTC to Eastern time
}
