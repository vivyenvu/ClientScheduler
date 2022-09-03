package model;

import helper.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class for object type Appointments.
 */
public class Appointments {
    private static ObservableList<Appointments> allAppointments;
    private int apptID;
    private String title;
    private String desc;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerIDFK;
    private int userIDFK;
    private int contactIDFK;
    private String contactName;

    /**
     * Constructor for Appointment object.
     * @param apptID appointment id
     * @param title appointment title
     * @param desc appointment description
     * @param location appointment location
     * @param type appointment type
     * @param start appointment start time and date
     * @param end appointment end time and date
     * @param customerIDFK customer id associated with this appointment
     * @param userIDFK user id associated with this appointment
     * @param contactIDFK contact id associated with this appointment
     * @param contactName name of contact associated with this appointment
     */
    public Appointments (int apptID, String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end,
                         int customerIDFK, int userIDFK, int contactIDFK, String contactName){
        this.apptID = apptID;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerIDFK = customerIDFK;
        this.userIDFK = userIDFK;
        this.contactIDFK = contactIDFK;
        this.contactName = contactName;
    }

    /**
     * Gets appointment ID
     * @return appointment id
     */
    public int getApptID() {
        return apptID;
    }

    /**
     * Gets appointment title
     * @return title of appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets appointment description
     * @return appointment description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Gets appointment location
     * @return location of appointment
     */
    public String getLocation(){
        return location;
    }

    /**
     * Gets appointment type
     * @return type of appointment
     */
    public String getType(){
        return type;
    }

    /**
     * Gets appointment start date and time
     * @return appointment start date and time
     */
    public LocalDateTime getStart(){
        return start;
    }

    /**
     * Gets appointment end date and time
     * @return appointment end date and time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Gets appointment's associated customer id
     * @return customer id
     */
    public int getCustomerIDFK(){
        return customerIDFK;
    }

    /**
     * Gets appointment's associated user id
     * @return user id
     */
    public int getUserIDFK(){
        return userIDFK;
    }

    /**
     * Gets appointment's associated contact id
     * @return contact id
     */
    public int getContactIDFK(){
        return contactIDFK;
    }

    /**
     * Gets contact name associated with appointment
     * @return contact name associated with appointment
     */
    public String getContactName(){
        return contactName;
    }

    /**
     * Gets list of all appointments
     * @return list of all appointments
     */
    public static ObservableList<Appointments> getAllAppts() {
        return allAppointments;
    }

    /**
     * Sets list of allAppointments to a particular list
     * @param toSet new list to assign as allAppointments
     */
    public static void setAllAppts(ObservableList<Appointments> toSet){
        allAppointments = toSet;
    }

    /**
     * Sets appointment id
     * @param id appointment id
     */
    public void setApptID(int id) {
        apptID = id;
    }

    /**
     * Sets appointment title
     * @param inputTitle appointment title
     */
    public void setTitle(String inputTitle){
        title = inputTitle;
    }

    /**
     * Sets appointment description
     * @param inputDesc appointment description
     */
    public void setDesc(String inputDesc){
        desc = inputDesc;
    }

    /**
     * Sets appointment location
     * @param inputLoc appointment location
     *
     */
    public void setLocation(String inputLoc){
        location = inputLoc;
    }

    /**
     * Sets appointment type
     * @param inputType appointment type
     */
    public void setType(String inputType){
        type = inputType;
    }

    /**
     * Sets appointment start date and time
     * @param inputStart start date and time
     */
    public void setStart(LocalDateTime inputStart) {
        start = inputStart;
    }

    /**
     * Sets appointment end date and time
     * @param inputEnd end date and time
     */
    public void setEnd(LocalDateTime inputEnd){
        end = inputEnd;
    }

    /**
     * Sets appointment customer id
     * @param id customer id
     */
    public void setCustomerIDFK(int id){
        customerIDFK = id;
    }

    /**
     * Sets appointment user id
     * @param id user id
     */
    public void setUserIDFK(int id){
        userIDFK = id;
    }

    /**
     * Sets appointment contact id
     * @param id contact id
     */
    public void setContactIDFK(int id){
        contactIDFK = id;
    }

    /**
     * Sets appointment contact name
     * @param name contact name
     */
    public void setContactName (String name){
        contactName = name;
    }

    /**
     * Creates and returns a list of start times, 30 minutes apart, that fall in the business's working hours
     * @return list of valid start time that are within business hours
     */
    public static ObservableList<LocalTime> getStartBizHours(){
        ObservableList<LocalTime> start = FXCollections.observableArrayList();
        start.add(LocalTime.of(12,00));
        start.add(LocalTime.of(12,30));
        start.add(LocalTime.of(13,00));
        start.add(LocalTime.of(13,30));
        start.add(LocalTime.of(14,00));
        start.add(LocalTime.of(14,30));
        start.add(LocalTime.of(15,00));
        start.add(LocalTime.of(15,30));
        start.add(LocalTime.of(16,00));
        start.add(LocalTime.of(16,30));
        start.add(LocalTime.of(17,00));
        start.add(LocalTime.of(17,30));
        start.add(LocalTime.of(18,00));
        start.add(LocalTime.of(18,30));
        start.add(LocalTime.of(19,00));
        start.add(LocalTime.of(19,30));
        start.add(LocalTime.of(20,00));
        start.add(LocalTime.of(20,30));
        start.add(LocalTime.of(21,00));
        start.add(LocalTime.of(21,30));
        start.add(LocalTime.of(22,00));
        start.add(LocalTime.of(22,30));
        start.add(LocalTime.of(23,00));
        start.add(LocalTime.of(23,30));
        start.add(LocalTime.of(00,00));
        start.add(LocalTime.of(00,30));
        start.add(LocalTime.of(1,00));
        start.add(LocalTime.of(1,30));
        return start;
    }

    /**
     * Creates and returns a list of end times, 30 minutes apart, that fall in the business's working hours
     * @return list of valid end time that are within business hours
     */
    public static ObservableList<LocalTime> getEndBizHours(){
        ObservableList<LocalTime> end = FXCollections.observableArrayList();
        end.add(LocalTime.of(12,30));
        end.add(LocalTime.of(13,00));
        end.add(LocalTime.of(13,30));
        end.add(LocalTime.of(14,00));
        end.add(LocalTime.of(14,30));
        end.add(LocalTime.of(15,00));
        end.add(LocalTime.of(15,30));
        end.add(LocalTime.of(16,00));
        end.add(LocalTime.of(16,30));
        end.add(LocalTime.of(17,00));
        end.add(LocalTime.of(17,30));
        end.add(LocalTime.of(18,00));
        end.add(LocalTime.of(18,30));
        end.add(LocalTime.of(19,00));
        end.add(LocalTime.of(19,30));
        end.add(LocalTime.of(20,00));
        end.add(LocalTime.of(20,30));
        end.add(LocalTime.of(21,00));
        end.add(LocalTime.of(21,30));
        end.add(LocalTime.of(22,00));
        end.add(LocalTime.of(22,30));
        end.add(LocalTime.of(23,00));
        end.add(LocalTime.of(23,30));
        end.add(LocalTime.of(00,00));
        end.add(LocalTime.of(00,30));
        end.add(LocalTime.of(1,00));
        end.add(LocalTime.of(1,30));
        end.add(LocalTime.of(2,00));
        return end;
    }

}
