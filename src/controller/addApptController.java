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
        String custName = addCustomerName.getText();
        String custPostal = addCustomerPostal.getText();
        String custPhone = addCustomerPhone.getText();
        String custAddress = addCustomerAddress.getText();
        String custDivision = (String) addCustomerFirstDiv.getValue();

        if (custName.isEmpty()){
            errorMessages += "Name field is required. \n";
        }
        if (custPostal.isEmpty()){
            errorMessages += "Postal code is required. \n";
        }
        if (custPhone.isEmpty()){
            errorMessages += "Phone number is required. \n";
        }
        if (custAddress.isEmpty()){
            errorMessages += "Address is required. \n";
        }
        if (custDivision == null){
            errorMessages += "First level division is required. Please select a country then division. \n";
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
