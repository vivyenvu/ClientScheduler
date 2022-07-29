package controller;

import DAO.*;
import helper.Util;
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
import java.util.ResourceBundle;

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

    public void onAddApptSaveBtn(ActionEvent actionEvent) throws IOException, SQLException {
        String errorMessages = "";
        String title = addApptTitle.getText();
        String desc = addApptDescription.getText();
        String loc = addApptLocation.getText();
        String type = addApptType.getText();
        LocalDate date = addApptDate.getValue();
        LocalTime startTime = LocalTime.of(13, 00);//(LocalTime)addApptStartTime.getValue();
        LocalTime endTime = LocalTime.of(14, 00);//(LocalTime)addApptEndTime.getValue();
        //String custID = (String) addApptCustomerID.getValue();
        //String userID = (String) addApptUserID.getValue();
        //String contactName =  (String) addApptContact.getValue();
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
        if (selectedContact == null){
            errorMessages += "Please select a contact. \n";
        }
        /*
        try{
            if (startTime.isEqual(null)) {
            errorMessages += "Please select a start time. \n";
            }
        }
        catch (NullPointerException e){
            errorMessages += "Please select a start time. \n";
        }

        try{
            if (endTime.isEqual(null)) {
                errorMessages += "Please select an end time. \n";
            }
        }
        catch (NullPointerException e){
            errorMessages += "Please select an end time. \n";
        }
*/
        // FIGURE OUT HOW TO VALIDATE BLANK LOCALTIME AFTER I POPULATE COMBOBXO WITH LOCALTIMES

        if (errorMessages != "") {
            Util.stringToAlert(errorMessages);
        }
        else {
            int custID = selectedCust.getCustomerID();
            int userID = selectedUser.getUserID();
            int contactID = selectedContact.getContactID();
            LocalDateTime start = LocalDateTime.of(date, startTime);
            LocalDateTime end = LocalDateTime.of(date, endTime);

            ApptDaoImpl.addAppt(title, desc, loc, type, start, end, custID, userID, contactID);

            Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onAddApptCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<LocalTime> times = Appointments.getBizHours();
        addApptStartTime.setItems(times);
        addApptStartTime.setVisibleRowCount(5);

        ObservableList<Contacts> contactDisplay = null;
        try {
            contactDisplay = ContactDaoImpl.getAllContacts();
            addApptContact.setItems(contactDisplay);
            addApptContact.setVisibleRowCount(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ObservableList<Customers> customerDisplay = null;
        try {
            customerDisplay = CustomerDaoImpl.getAllCustomers();
            addApptCustomerID.setItems(customerDisplay);
            addApptCustomerID.setVisibleRowCount(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
