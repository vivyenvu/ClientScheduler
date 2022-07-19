package helper;

import DAO.Query;
import javafx.scene.control.Alert;

import java.rmi.registry.LocateRegistry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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

    public String contactIDToName(int id) throws SQLException {
        String name =""; //remove initialization later
        ResultSet rs = Query.getRS("SELECT Contact_Name FROM contacts WHERE Contact_ID = '" + id +"'");
        while (rs.next()){
            name = rs.getString("Contact_Name");
        }
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

    public LocalDateTime systemToUTC(LocalDateTime origin){
        ZonedDateTime zonedOrigin = origin.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedTarget = zonedOrigin.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime target = zonedTarget.toLocalDateTime();

        return target;
    }

    public LocalDateTime UTCToSystem(LocalDateTime utc){
        ZonedDateTime zonedUtc = utc.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedTarget = zonedUtc.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime target = zonedTarget.toLocalDateTime();

        return target;
    }

    public LocalDateTime UTCToEastern(LocalDateTime utc){
        ZonedDateTime zonedUtc = utc.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedEastern = zonedUtc.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalDateTime eastern = zonedEastern.toLocalDateTime();

        return eastern;
    }
}
