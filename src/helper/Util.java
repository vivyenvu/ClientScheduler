package helper;

import DAO.Query;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
        String name =""; //remove initialization later?
        ResultSet rs = Query.getRS("SELECT Contact_Name FROM contacts WHERE Contact_ID = '" + id +"'");
        while (rs.next()){
            name = rs.getString("Contact_Name");
        }
        return name;
    }

    public int usernameToID (String name) throws SQLException {
        int id = 0;// remove later
        ResultSet rs = Query.getRS("SELECT User_ID FROM users WHERE User_Name = '" +name +"'");
        while (rs.next()){
            id = rs.getInt("User_ID");
        }
        return id;
    }

    public String userIDToName (int id) throws SQLException {
        String name = ""; //remove later
        ResultSet rs = Query.getRS("SELECT User_Name FROM users WHERE User_ID = '" + id +"'");
        while (rs.next()){
            name = rs.getString("User_Name");
        }
        return name;
    }

    public static int countryToCountryID(String name) throws SQLException {
        int id = 0;// remove later
        ResultSet rs = Query.getRS("SELECT Country_ID FROM countries WHERE Country = '" +name +"'");
        while (rs.next()){
            id = rs.getInt("Country_ID");
        }
        return id;
    }

    public static int firstDivToID (String name) throws SQLException {
        int id = 0;// remove later
        ResultSet rs = Query.getRS("SELECT Division_ID FROM first_level_divisions WHERE Division = '" +name +"'");
        while (rs.next()){
            id = rs.getInt("Division_ID");
        }
        return id;
    }

    public static String firstIDtoDiv(int id) throws SQLException {
        String name = ""; //remove later
        ResultSet rs = Query.getRS("SELECT Division FROM first_level_divisions WHERE Division_ID = '" + id +"'");
        while (rs.next()){
            name = rs.getString("Division");
        }
        return name;
    }

    public static String divIDToCountry(int id) throws SQLException {
        String countryName = "";
        int countryID =0;
        ResultSet rs = Query.getRS("SELECT Country_ID FROM first_level_divisions WHERE Division_ID = '"+id+"'");
        while (rs.next()){
            countryID = rs.getInt("Country_ID");
        }

        ResultSet rsc = Query.getRS("SELECT Country FROM countries WHERE Country_ID = '" +countryID+"'");
        ResultSetMetaData rsmd = rsc.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rsc.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                countryName = rsc.getString(i);
                //System.out.println(countryName);
            }
        }

        return countryName;
    }
    public static LocalDateTime systemToUTC(LocalDateTime origin){
        ZonedDateTime zonedOrigin = origin.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedTarget = zonedOrigin.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime target = zonedTarget.toLocalDateTime();

        return target;
    }

    public static LocalDateTime UTCToSystem(LocalDateTime utc){
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

    /*public LocalDateTime easternToUTC(LocalDateTime eastern){
        ZonedDateTime zonedEastern = eastern.atZone(ZoneId.of("US/Eastern"));
        ZonedDateTime zonedUtc = zonedEastern.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime utc = zonedUtc.toLocalDateTime();

        return utc;
    }*/
}
