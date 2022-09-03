package helper;

import DAO.Query;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointments;
import model.Users;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Helper class for repetitive tasks like converting between time zones.
 */
public class Util {

    /**
     * Creates an alert with a given String message.
     * @param message text to be displayed in the alert
     */
    public static void stringToAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Provides country id associated with given country name.
     * @param name country name
     * @return country id
     * @throws SQLException
     */
    public static int countryToCountryID(String name) throws SQLException {
        int id = 0;
        ResultSet rs = Query.getRS("SELECT Country_ID FROM countries WHERE Country = '" +name +"'");
        while (rs.next()){
            id = rs.getInt("Country_ID");
        }
        return id;
    }

    /**
     * Provides first level division id for a given name of a first level division.
     * @param name first level division name
     * @return first level division id
     * @throws SQLException
     */
    public static int firstDivToID (String name) throws SQLException {
        int id = 0;
        ResultSet rs = Query.getRS("SELECT Division_ID FROM first_level_divisions WHERE Division = '" +name +"'");
        while (rs.next()){
            id = rs.getInt("Division_ID");
        }
        return id;
    }

    /**
     * Provides the name associated with a first level division id.
     * @param id first level division id
     * @return first level division name
     * @throws SQLException
     */
    public static String firstIDtoDiv(int id) throws SQLException {
        String name = "";
        ResultSet rs = Query.getRS("SELECT Division FROM first_level_divisions WHERE Division_ID = '" + id +"'");
        while (rs.next()){
            name = rs.getString("Division");
        }
        return name;
    }

    /**
     * Provides name of country associated with a first level division id.
     * @param id first level division id
     * @return name of country
     * @throws SQLException
     */
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
            }
        }

        return countryName;
    }

    /**
     * Converts system DateTime to UTC.
     * @param origin LocalDateTime in system's time zone
     * @return LocalDateTime converted to UTC
     */
    public static LocalDateTime systemToUTC(LocalDateTime origin){
        ZonedDateTime zonedOrigin = origin.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedTarget = zonedOrigin.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime target = zonedTarget.toLocalDateTime();

        return target;
    }

    /**
     * Converts UTC DateTime to system time zone.
     * @param utc LocalDateTime UTC
     * @return LocalDateTime converted to system's time zone
     */
    public static LocalDateTime UTCToSystem(LocalDateTime utc){
        ZonedDateTime zonedUtc = utc.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedTarget = zonedUtc.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime target = zonedTarget.toLocalDateTime();

        return target;
    }

    /**
     * Alert if there is an appointment starting in the next 15 minutes for this user.
     * If not, an alert notifies the user that there are no upcoming appointments.
     * @param user
     * @return
     */
    public static String upcomingMessage(Users user){
        int userID = user.getUserID();
        ObservableList<Appointments> allAppts = Appointments.getAllAppts();
        String upcomingAppts = "";
        for (Appointments appt : allAppts){
            if (userID == appt.getUserIDFK()){
                LocalTime startTime = appt.getStart().toLocalTime();
                LocalTime loginTime = LocalTime.now();
                LocalDate loginDate = LocalDate.now();

                long timeDifference = ChronoUnit.MINUTES.between(loginTime, startTime);
                int id = appt.getApptID();
                LocalDateTime dateTime = appt.getStart();
                LocalDate date = dateTime.toLocalDate();
                LocalTime time = dateTime.toLocalTime();

                if (timeDifference >= 0 && timeDifference <= 15 && loginDate.equals(date)) {
                    upcomingAppts += "Upcoming appointment with ID " +id+" on " +date+ " at " +time+ ". \n";
                }
            }
        }
        if (upcomingAppts == ""){
            return "No upcoming appointments.";
        }
        return upcomingAppts;
    }
}
