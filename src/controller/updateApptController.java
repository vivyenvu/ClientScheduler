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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class updateApptController implements Initializable{
    public TextField updateApptTitle;
    public TextField updateApptDescription;
    public TextField updateApptLocation;
    public ComboBox <Contacts> updateApptContact;
    public DatePicker updateApptDate;
    public ComboBox <LocalTime> updateApptStartTime;
    public ComboBox <LocalTime> updateApptEndTime;
    public ComboBox <Customers> updateApptCustomerID;
    public ComboBox <Users> updateApptUserID;
    public TextField updateApptType;
    public TextField updateApptID;

    public void onUpdateApptSaveBtn(ActionEvent actionEvent) throws IOException {
        String errorMessages = "";
        int clear = 0;
        int count = 0;
        int apptID = Integer.parseInt(updateApptID.getText());
        String title = updateApptTitle.getText();
        String desc = updateApptDescription.getText();
        String loc = updateApptLocation.getText();
        String type = updateApptType.getText();
        LocalDate date = updateApptDate.getValue();
        LocalTime startTime = updateApptStartTime.getValue();
        LocalTime endTime = updateApptEndTime.getValue();
        Customers selectedCust = updateApptCustomerID.getSelectionModel().getSelectedItem();
        Users selectedUser = updateApptUserID.getSelectionModel().getSelectedItem();
        Contacts selectedContact = updateApptContact.getSelectionModel().getSelectedItem();

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
            System.out.println ("Need to select a start and end time. ");
        }

        if (errorMessages != "") {
            Util.stringToAlert(errorMessages);
        }

        else{
            //validates no time overlap
            ObservableList<Appointments> allAppts = Appointments.getAllAppts();
            for (Appointments appt : allAppts){
                if ((appt.getCustomerIDFK() == selectedCust.getCustomerID() && (appt.getApptID() != apptID))){
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

            ApptDaoImpl.updateAppt(apptID, title, desc, loc, type, start, end, custID, userID, contactID);

            Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onUpdateApptCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
    public void sendAppointment(Appointments appt) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        Contacts selectedContact = null;
        for (Contacts c : Contacts.getAllContacts()){
            if (c.getContactID() == appt.getContactIDFK()){
                selectedContact = c;
            }
        }
        updateApptID.setText(String.valueOf(appt.getApptID()));
        updateApptTitle.setText(appt.getTitle());
        updateApptDescription.setText(appt.getDesc());
        updateApptLocation.setText(appt.getLocation());
        updateApptContact.getSelectionModel().select(selectedContact);
        updateApptType.setText(appt.getType());

        LocalDateTime chooseDate = appt.getStart();
        String formattedDate = chooseDate.format(formatter);
        LocalDate selectedDate = LocalDate.parse(formattedDate);
        updateApptDate.setValue(selectedDate);

        updateApptStartTime.getSelectionModel().select(appt.getStart().toLocalTime());
        updateApptEndTime.getSelectionModel().select(appt.getEnd().toLocalTime());

        Customers selectedCustomer = null;
        for (Customers cust : Customers.getAllCustomers()){
            if (cust.getCustomerID() == appt.getCustomerIDFK()){
                selectedCustomer = cust;
            }
        }
        updateApptCustomerID.getSelectionModel().select(selectedCustomer);

        Users selectedUser = null;
        for (Users u : Users.getAllUsers()){
            if (u.getUserID() == appt.getUserIDFK()){
                selectedUser = u;
            }
        }
        updateApptUserID.getSelectionModel().select(selectedUser);
    }

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
        updateApptStartTime.setItems(startLocal);
        updateApptStartTime.setVisibleRowCount(5);

        //applies offset to end time and populates combobox
        ObservableList<LocalTime> endUTC = Appointments.getEndBizHours();
        ObservableList<LocalTime> endLocal = FXCollections.observableArrayList();

        for (LocalTime time : endUTC){
            LocalTime local = time.minusHours(diff);
            endLocal.add(local);
        }
        updateApptEndTime.setItems(endLocal);
        updateApptEndTime.setVisibleRowCount(5);

        //populates Contacts combobox
        ObservableList<Contacts> contactDisplay = null;
        try {
            contactDisplay = ContactDaoImpl.getAllContacts();
            updateApptContact.setItems(contactDisplay);
            updateApptContact.setVisibleRowCount(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //populates Customer ID combobox
        ObservableList<Customers> customerDisplay = null;
        try {
            customerDisplay = CustomerDaoImpl.getAllCustomers();
            updateApptCustomerID.setItems(customerDisplay);
            updateApptCustomerID.setVisibleRowCount(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //populates Contacts ID combobox
        ObservableList<Users> userDisplay = null;
        try {
            userDisplay = UserDaoImpl.getAllUsers();
            updateApptUserID.setItems(userDisplay);
            updateApptUserID.setVisibleRowCount(5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setAllContacts()
        try {
            ObservableList<Contacts> loadContacts = ContactDaoImpl.getAllContacts();
            Contacts.setAllContacts(loadContacts);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
