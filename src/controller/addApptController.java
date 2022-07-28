package controller;

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

    public void onAddApptSaveBtn(ActionEvent actionEvent) {
        String errorMessages = "";
        String title = addApptTitle.getText();
        String desc = addApptDescription.getText();
        String loc = addApptLocation.getText();
        String type = addApptType.getText();
        LocalDateTime start = addApptStartTime.getValue();
        LocalDateTime end = addApptEndTime.getValue();
        int custID = Integer.parseInt((String)addApptCustomerID.getValue());
        int userID = Integer.parseInt((String)addApptUserID.getValue());
        int contactID = Integer.parseInt((String)addApptContact.getValue());

        //String custDivision = (String) addCustomerFirstDiv.getValue();

        if (title.isEmpty()){
            errorMessages += "Title is required. \n";
        }
        if (desc.isEmpty()){
            errorMessages += "Description is required. \n";
        }
        if (loc.isEmpty()){
            errorMessages += "Location is required. \n";
        }
        if (type.isEmpty()){
            errorMessages += "Type is required. \n";
        }
        if (start.isEmpty()){
            errorMessages += "Please select a start time. \n";
        }
        if (end.isEmpty()){
            errorMessages += "Please select an end time. \n";
        }
        if (custID.isEmpty()){
            errorMessages += "Please select a customer ID. \n";
        }
        if (userID.isEmpty()){
            errorMessages += "Please select a user ID. \n";
        }
        if (contactID.isEmpty()){
            errorMessages += "Please select a contact ID. \n";
        }


        if (errorMessages != "") {
            Util.stringToAlert(errorMessages);
        }
        else{
            int custDivID = Util.firstDivToID(custDivision);
            CustomerDaoImpl.addCustomer(custName, custAddress, custPostal, custPhone, custDivID);

            Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
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
