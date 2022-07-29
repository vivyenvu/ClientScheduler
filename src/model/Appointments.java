package model;

import helper.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointments {
    //private ObservableList<Appointments> allAppointments; //DO I NEED THIS
    //private static ObservableList<LocalTime> bizHours = FXCollections.observableArrayList();
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

    public int getApptID() {
        return apptID;
    }
    public String getTitle() {
        return title;
    }
    public String getDesc() {
        return desc;
    }
    public String getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
    public LocalDateTime getStart(){
        return start;
    }
    public LocalDateTime getEnd() {
        return end;
    }
    public int getCustomerIDFK(){
        return customerIDFK;
    }
    public int getUserIDFK(){
        return userIDFK;
    }
    public int getContactIDFK(){
        return contactIDFK;
    }
    public String getContactName(){
        return contactName;
    }
    public static ObservableList<LocalTime> getBizHours(){
        ObservableList<LocalTime> bizHours = FXCollections.observableArrayList();
        //bizHours.add(LocalTime.of(13,00));
        LocalTime end = LocalTime.of(23, 00);//Includes 10pm, so make sure to remove 30 min for start time, or validate that start<end
        LocalTime toAdd = LocalTime.of(12, 00);

        while (toAdd.isBefore(end)){
            bizHours.add(toAdd);
            toAdd.plusMinutes(30);
        }
        return bizHours;
    //NEED TO CONVERT TO UTC and then to local time before letting users choose
        //Did this manually 8 est -> 12 utc. 22 est -> 2 est
    }
    public void setApptID(int id) {
        apptID = id;
    }
    public void setTitle(String inputTitle){
        title = inputTitle;
    }
    public void setDesc(String inputDesc){
        desc = inputDesc;
    }
    public void setLocation(String inputLoc){
        location = inputLoc;
    }
    public void setType(String inputType){
        type = inputType;
    }
    public void setStart(LocalDateTime inputStart) {
        start = inputStart;
    }
    public void setEnd(LocalDateTime inputEnd){
        end = inputEnd;
    }
    public void setCustomerIDFK(int id){
        customerIDFK = id;
    }
    public void setUserIDFK(int id){
        userIDFK = id;
    }
    public void setContactIDFK(int id){
        contactIDFK = id;
    }
    public void setContactName (String name){
        contactName = name;
    }
    /*public static void addBizHours(LocalTime hour){
        bizHours.add(hour);
    }*/
}
