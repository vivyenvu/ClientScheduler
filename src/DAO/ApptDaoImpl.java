package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ApptDaoImpl {
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

    public static void addAppt(String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int customerIDFK, int userIDFK, int contactIDFK) throws SQLException {
        int apptID=0; //might not be a good initialization
        String sql = "INSERT INTO appointments VALUES (?,?,?,?,?,?,?,NULL, NULL, NULL, NULL,?,?,?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ps.getGeneratedKeys();
        while(rs.next()) {
            apptID = rs.getInt(1);
        }
        ps.setInt(1, apptID);
        ps.setString (2, title);
        ps.setString(3, desc);
        ps.setString(4, loc);
        ps.setString(5, type);
        ps.setLocaleDateTime(6, start);
        ps.setLocaleDateTime(7, end);
        ps.setInt(8, customerIDFK);
        ps.setInt(9, userIDFK);
        ps.setInt(10, contactIDFK);
        ps.execute();
    }
}
