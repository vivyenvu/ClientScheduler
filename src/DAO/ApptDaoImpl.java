package DAO;

import helper.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customers;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Interacts with Appointments information from the database.
 */
public class ApptDaoImpl {

    /**
     * Populates a list of all appointments and their information from the database.
     * @return list of all appointments
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllAppts() throws SQLException {
        ObservableList<Appointments> allAppts = FXCollections.observableArrayList();
        ResultSet result = Query.getRS("select * from appointments " +
                                            "INNER JOIN contacts " +
                                            "ON appointments.Contact_ID = contacts.Contact_ID ");
        while(result.next()){
            int apptID = result.getInt("Appointment_ID");
            String apptTitle = result.getString("Title");
            String apptDesc = result.getString("Description");
            String apptLoc = result.getString("Location");
            String apptType = result.getString("Type");

            LocalDateTime apptStart = result.getTimestamp("Start").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
            apptStart = Util.UTCToSystem(apptStart);

            LocalDateTime apptEnd = result.getTimestamp("End").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime(); //better than .toLocalDateTime()
            apptEnd = Util.UTCToSystem(apptEnd);

            int custID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            Appointments apptResult = new Appointments(apptID, apptTitle, apptDesc, apptLoc, apptType, apptStart, apptEnd, custID, userID, contactID, contactName);
            allAppts.add(apptResult);
        }
        Appointments.setAllAppts(allAppts);
        return allAppts;
    }

    /**
     * Creates a new entry in the appointments table of the database.
     * @param title appointment title
     * @param desc appointment description
     * @param loc appointment location
     * @param type appointment type
     * @param start appointment start time and date
     * @param end appointment end time and date
     * @param customerIDFK appointment associated customer id
     * @param userIDFK appointment associated user id
     * @param contactIDFK appointment associated contact id
     * @throws SQLException
     */
    public static void addAppt(String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int customerIDFK, int userIDFK, int contactIDFK) throws SQLException {
        int apptID=0;
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
        ps.setObject(6, start);
        ps.setObject(7, end);
        ps.setInt(8, customerIDFK);
        ps.setInt(9, userIDFK);
        ps.setInt(10, contactIDFK);
        ps.execute();
    }

    /**
     * Deletes a row from the appointments table of the database.
     * @param id id of the appointment to be deleted
     * @throws SQLException
     */
    public static void deleteAppt(int id) throws SQLException {
        //delete from appointments THEN delete from customer table
        try{
            PreparedStatement psa = Query.getPS("DELETE FROM appointments WHERE Appointment_ID = ?");
            psa.setInt(1,id);
            psa.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Updates information of existing entry in the appointments table of database.
     * @param apptID appointment id
     * @param title appointment title
     * @param desc appointment description
     * @param loc appointment location
     * @param type appointment type
     * @param start appointment start time and date
     * @param end appointment time and date
     * @param customerIDFK appointment customer id
     * @param userIDFK appointment user id
     * @param contactIDFK appointment contact id
     */
    public static void updateAppt (int apptID, String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int customerIDFK, int userIDFK, int contactIDFK){
        try{
            PreparedStatement ps = Query.getPS("UPDATE appointments set Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?");
            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setString(3, loc);
            ps.setString(4, type);
            ps.setObject(5, start);
            ps.setObject(6, end);
            ps.setInt(7, customerIDFK);
            ps.setInt(8, userIDFK);
            ps.setInt(9, contactIDFK);
            ps.setInt(10, apptID);
            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets list of appointments that fall within the upcoming week.
     * @return list of appointments that fall within the upcoming week.
     * @throws SQLException
     */
    public static ObservableList<Appointments> getWeekAppts() throws SQLException {
        ObservableList<Appointments> weekAppts = FXCollections.observableArrayList();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime weekEnd = today.plusDays(7);

        PreparedStatement ps = Query.getPS("SELECT * FROM appointments " +
                "INNER JOIN contacts " +
                "ON appointments.Contact_ID = contacts.Contact_ID WHERE " +
                "Start > ? AND Start < ?");
        ps.setDate(1, Date.valueOf(today.toLocalDate()));
        ps.setDate(2, Date.valueOf(weekEnd.toLocalDate()));
        ps.execute();

        ResultSet result = ps.executeQuery();
        while(result.next()){
            int apptID = result.getInt("Appointment_ID");
            String apptTitle = result.getString("Title");
            String apptDesc = result.getString("Description");
            String apptLoc = result.getString("Location");
            String apptType = result.getString("Type");
            LocalDateTime apptStart = result.getTimestamp("Start").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
            apptStart = Util.UTCToSystem(apptStart);
            LocalDateTime apptEnd = result.getTimestamp("End").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime(); //better than .toLocalDateTime()
            apptEnd = Util.UTCToSystem(apptEnd);
            int custID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            Appointments apptResult = new Appointments(apptID, apptTitle, apptDesc, apptLoc, apptType, apptStart, apptEnd, custID, userID, contactID, contactName);
            weekAppts.add(apptResult);
        }
        return weekAppts;
    }

    /**
     * Gets list of appointments that fall within the upcoming month.
     * @return list of appointments that fall within the upcoming month.
     * @throws SQLException
     */
    public static ObservableList<Appointments> getMonthAppts() throws SQLException {
        ObservableList<Appointments> monthAppts = FXCollections.observableArrayList();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime monthEnd = today.plusMonths(1);

        PreparedStatement ps = Query.getPS("SELECT * FROM appointments " +
                "INNER JOIN contacts " +
                "ON appointments.Contact_ID = contacts.Contact_ID WHERE " +
                "Start > ? AND Start < ?");
        ps.setDate(1, Date.valueOf(today.toLocalDate()));
        ps.setDate(2, Date.valueOf(monthEnd.toLocalDate()));
        ps.execute();

        ResultSet result = ps.executeQuery();
        while(result.next()){
            int apptID = result.getInt("Appointment_ID");
            String apptTitle = result.getString("Title");
            String apptDesc = result.getString("Description");
            String apptLoc = result.getString("Location");
            String apptType = result.getString("Type");
            LocalDateTime apptStart = result.getTimestamp("Start").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
            apptStart = Util.UTCToSystem(apptStart);
            LocalDateTime apptEnd = result.getTimestamp("End").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime(); //better than .toLocalDateTime()
            apptEnd = Util.UTCToSystem(apptEnd);
            int custID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            Appointments apptResult = new Appointments(apptID, apptTitle, apptDesc, apptLoc, apptType, apptStart, apptEnd, custID, userID, contactID, contactName);
            monthAppts.add(apptResult);
        }
        return monthAppts;
    }

    /**
     * Gets a list of unique types of appointments.
     * @return a list of unique types of appointments.
     * @throws SQLException
     */
    public static ObservableList<String> getAllTypes() throws SQLException {
        ObservableList<String> allTypes = FXCollections.observableArrayList();
        ResultSet result = Query.getRS("SELECT DISTINCT Type FROM appointments");
        while(result.next()){
            String addType = result.getString("Type");
            allTypes.add(addType);
        }
        return allTypes;
    }

    /**
     * Gets the count of appointments of a certain type that falls within a certain month.
     * @param type type of appointment
     * @param month month of appointment
     * @return count of appointments that match the type and month.
     * @throws SQLException
     */
    public static int getCount(String type, int month) throws SQLException {
        int count = 0;
        PreparedStatement ps = Query.getPS("SELECT COUNT(*) FROM appointments WHERE Type = ? AND MONTH(Start) = ?");
        ps.setString(1, type);
        ps.setInt(2, month);

        ResultSet result = ps.executeQuery();
        while(result.next()) {
            count = result.getInt(1);
        }
        return count;
    }
}
