package controller;

import DAO.ApptDaoImpl;
import DAO.CustomerDaoImpl;
import helper.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class addApptController {
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
        LocalDateTime start = addApptStartTime.getValue();
        LocalDateTime end = addApptEndTime.getValue();
        String custID = (String) addApptCustomerID.getValue();
        String userID = (String) addApptUserID.getValue();
        String contactID = (String) addApptContact.getValue();
        //String custDivision = (String) addCustomerFirstDiv.getValue();

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
        if (start.isEqual(null)) {
            errorMessages += "Please select a start time. \n";
        }
        if (end.isEqual(null)) {
            errorMessages += "Please select an end time. \n";
        }

        try {
            int validatedCustID = Integer.parseInt(custID);
        } catch (NumberFormatException e) {
            errorMessages += "Customer ID is required. ";
        }
        try {
            int validatedUserID = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            errorMessages += "User ID is required. ";
        }
        try {
            int validatedContactID = Integer.parseInt(contactID);
        } catch (NumberFormatException e) {
            errorMessages += "Contact ID is required. ";
        }

        if (errorMessages != "") {
            Util.stringToAlert(errorMessages);
        } else {
            int validatedCustID = Integer.parseInt(custID);
            int validatedUserID = Integer.parseInt(userID);
            int validatedContactID = Integer.parseInt(contactID);
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
}
