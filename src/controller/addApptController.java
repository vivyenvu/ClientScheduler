package controller;

import DAO.ApptDaoImpl;
import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
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
import model.Appointments;
import model.Countries;

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
    public ComboBox addApptContact;
    public DatePicker addApptDate;
    public ComboBox addApptStartTime;
    public ComboBox addApptEndTime;
    public ComboBox addApptCustomerID;
    public ComboBox addApptUserID;
    public TextField addApptType;

    public void onAddApptSaveBtn(ActionEvent actionEvent) throws IOException, SQLException {
        String errorMessages = "";
        String title = addApptTitle.getText();
        String desc = addApptDescription.getText();
        String loc = addApptLocation.getText();
        String type = addApptType.getText();
        LocalDate date = addApptDate.getValue();
        LocalTime startTime = (LocalTime)addApptStartTime.getValue();
        LocalTime endTime = (LocalTime)addApptEndTime.getValue();
        String custID = (String) addApptCustomerID.getValue();
        String userID = (String) addApptUserID.getValue();
        String contactID = (String) addApptContact.getValue();

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
        try {
            int validatedCustID = Integer.parseInt(custID);
        } catch (NumberFormatException e) {
            errorMessages += "Customer ID is required. \n";
        }
        try {
            int validatedUserID = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            errorMessages += "User ID is required. \n";
        }
        try {
            int validatedContactID = Integer.parseInt(contactID);
        } catch (NumberFormatException e) {
            errorMessages += "Contact ID is required. \n";
        }

        if (errorMessages != "") {
            Util.stringToAlert(errorMessages);
        }
        else {
            int validatedCustID = Integer.parseInt(custID);
            int validatedUserID = Integer.parseInt(userID);
            int validatedContactID = Integer.parseInt(contactID);
            LocalDateTime start = LocalDateTime.of(date, startTime);
            LocalDateTime end = LocalDateTime.of(date, endTime);

            ApptDaoImpl.addAppt(title, desc, loc, type, start, end, validatedCustID, validatedUserID, validatedContactID);

            Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onAddApptCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<LocalTime> times = Appointments.getBizHours();
        addApptStartTime.setItems(times);
        addApptStartTime.setVisibleRowCount(5);
    }
}
