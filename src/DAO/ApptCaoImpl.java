package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ApptCaoImpl {
    public static ObservableList<Appointments> getAllAppts() throws SQLException {
        ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
        ResultSet result = Query.getRS("select * from appointments");
        while(result.next()){
            int apptID = result.getInt("Appointment_ID");
            String apptTitle = result.getString("Title");
            String apptDesc = result.getString("Description");
            String apptLoc = result.getString("Location");
            String apptType = result.getString("Type");
            LocalDateTime apptStart = result.getTimestamp("Start").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
            LocalDateTime apptEnd = result.getTimestamp("End").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime(); //better than .toLocalDateTime()
            int custID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            Appointments apptResult = new Appointments(apptID, apptTitle, apptDesc, apptLoc, apptType, apptStart, apptEnd, custID, userID, contactID);
            allAppts.add(apptResult);
        }
        return allAppts;
    }
}
