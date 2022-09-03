package controller;

import DAO.*;
import helper.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * Allows user to add a new apointment. Includes fields id, title, description, location, contact, date, start time, end time, customer id, user id, and type.
 */
public class addApptController implements Initializable {
    public TextField addApptID;
    public TextField addApptTitle;
    public TextField addApptDescription;
    public TextField addApptLocation;
    public ComboBox <Contacts> addApptContact;
    public DatePicker addApptDate;
    public ComboBox <LocalTime> addApptStartTime;
    public ComboBox <LocalTime> addApptEndTime;
    public ComboBox <Customers> addApptCustomerID;
    public ComboBox <Users> addApptUserID;
    public TextField addApptType;

    /**
     * When save button is clicked, this method will validate that all fields are not blank and valid.
     * If there is invalid data, an error will pop up. Otherwise, a new appointment is created and
     * added to the appointments table of the database.
     * @param actionEvent when Save button is clicked
     * @throws IOException
     * @throws SQLException
     */
    public void onAddApptSaveBtn(ActionEvent actionEvent) throws IOException, SQLException {
        String errorMessages = "";
        int count = 0;
        int clear = 0;
        String title = addApptTitle.getText();
        String desc = addApptDescription.getText();
        String loc = addApptLocation.getText();
        String type = addApptType.getText();
        LocalDate date = addApptDate.getValue();
        LocalTime startTime = addApptStartTime.getValue();
        LocalTime endTime = addApptEndTime.getValue();
        Customers selectedCust = addApptCustomerID.getSelectionModel().getSelectedItem();
        Users selectedUser = addApptUserID.getSelectionModel().getSelectedItem();
        Contacts selectedContact = addApptContact.getSelectionModel().getSelectedItem();

        if (title.isEmpty()) {
            errorMessages += "Title is required. \n";
        }
        if (desc.isEmpty()) {
            errorMessages += "Description is required. \n";
        }
        if (loc.isEmpty()) {
            errorMessages += "Location is required. \n";
        }
        if (selectedContact == null){
            errorMessages += "Please select a contact. \n";
        }
        if (type.isEmpty()) {
            errorMessages += "Type is required. \n";
        }
        if (date == null){
            errorMessages += "Please select a date. \n";
        }
        if (selectedCust == null){
            errorMessages += "Please select a Customer ID. \n";
        }
        if (selectedUser == null){
            errorMessages += "Please select a User ID. \n";
        }

        try{
            if (startTime.equals(null)) {
            errorMessages += "Please select a start time. \n";
            }
        }
        catch (NullPointerException e){
            errorMessages += "Please select a start time. \n";
        }

        try{
            if (endTime.equals(null)) {
                errorMessages += "Please select an end time. \n";
            }
        }
        catch (NullPointerException e){
            errorMessages += "Please select an end time. \n";
        }

        try{
            if (startTime.isAfter(endTime) || startTime.equals(endTime)){
                errorMessages += "Start time needs to come before end time. \n";
            }
        }
        catch (NullPointerException e){
            System.out.println ("Need to select a start and end time. \n");
        }

        //validates all fields are filled
        if (errorMessages != "") {
            Util.stringToAlert(errorMessages);
        }
        else{
            //validates no time overlap
            ObservableList<Appointments> allAppts = Appointments.getAllAppts();
            for (Appointments appt : allAppts){
                if (appt.getCustomerIDFK() == selectedCust.getCustomerID()){
                    LocalDateTime oldStart = appt.getStart();
                    LocalDateTime oldEnd = appt.getEnd();
                    LocalDateTime newStart = LocalDateTime.of(date, startTime);
                    LocalDateTime newEnd = LocalDateTime.of(date, endTime);
                    count++;
                    if ((newEnd.isBefore(oldStart) ||newEnd.equals(oldStart)) || (oldEnd.isBefore(newStart) || oldEnd.equals(newStart))) {
                        clear++;
                    }
                }
            }
            if (count != clear) {
                Util.stringToAlert("Cannot create appointment because there are overlapping appointments. \n");
            }
            else {
                int custID = selectedCust.getCustomerID();
                int userID = selectedUser.getUserID();
                int contactID = selectedContact.getContactID();
                LocalDateTime start = Util.systemToUTC(LocalDateTime.of(date, startTime));
                LocalDateTime end = Util.systemToUTC(LocalDateTime.of(date, endTime));

                ApptDaoImpl.addAppt(title, desc, loc, type, start, end, custID, userID, contactID);

                Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    /**
     * Cancel button changes scene back to Main Menu
     * @param actionEvent when Cancel button is clicked
     * @throws IOException
     */
    public void onAddApptCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Populates combo boxes for appointments. These include Start/End comboboxes with business hours based off of user's timezone,
     * Contacts, Customer ID, and Contacts ID.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //calculate time offset between system timezone and utc in hours
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println(currentZone);
        long now = System.currentTimeMillis();
        long diff = TimeZone.getTimeZone("UTC").getOffset(now) - TimeZone.getTimeZone(currentZone).getOffset(now);
        diff = diff/3600000; // turns milliseconds into hours

        //applies offset to start time and populates combobox
        ObservableList<LocalTime> startUTC = Appointments.getStartBizHours();
        ObservableList<LocalTime> startLocal = FXCollections.observableArrayList();

        for (LocalTime time : startUTC){
            LocalTime local = time.minusHours(diff);
            startLocal.add(local);
        }
        addApptStartTime.setItems(startLocal);
        addApptStartTime.setVisibleRowCount(5);

        //applies offset to end time and populates combobox
        ObservableList<LocalTime> endUTC = Appointments.getEndBizHours();
        ObservableList<LocalTime> endLocal = FXCollections.observableArrayList();

        for (LocalTime time : endUTC){
            LocalTime local = time.minusHours(diff);
            endLocal.add(local);
        }
        addApptEndTime.setItems(endLocal);
        addApptEndTime.setVisibleRowCount(5);

        //populates Contacts combobox
        ObservableList<Contacts> contactDisplay = null;
        try {
            contactDisplay = ContactDaoImpl.getAllContacts();
            addApptContact.setItems(contactDisplay);
            addApptContact.setVisibleRowCount(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //populates Customer ID combobox
        ObservableList<Customers> customerDisplay = null;
        try {
            customerDisplay = CustomerDaoImpl.getAllCustomers();
            addApptCustomerID.setItems(customerDisplay);
            addApptCustomerID.setVisibleRowCount(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //populates Contacts ID combobox
        ObservableList<Users> userDisplay = null;
        try {
            userDisplay = UserDaoImpl.getAllUsers();
            addApptUserID.setItems(userDisplay);
            addApptUserID.setVisibleRowCount(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
