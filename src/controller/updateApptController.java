package controller;

import DAO.Query;
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
import java.time.LocalTime;
import java.util.ResourceBundle;

public class updateApptController{
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

    public void onUpdateApptSaveBtn(ActionEvent actionEvent) {
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
        updateCustomerID.setText(String.valueOf(customer.getCustomerID()));
        updateCustomerName.setText(customer.getCustomerName());
        updateCustomerAddress.setText(customer.getAddress());
        updateCustomerPostal.setText(customer.getPostalCode());
        updateCustomerPhone.setText(customer.getPhone());

        //set Country combobox
        int divID = customer.getDivisionIDFK();
        Countries selectedCountry = new Countries (divID, Util.divIDToCountry(divID));
        updateCustomerCountry.getSelectionModel().select(selectedCountry);

        //load First Division combobox based on selected country
        ObservableList<String> allDivs= FXCollections.observableArrayList();
        int countryID = Util.countryToCountryID(selectedCountry.getCountry()); //DOES THE HELPER NEED TO BE STATIC
        ResultSet rs = Query.getRS ("SELECT Division FROM first_level_divisions WHERE Country_ID = '" +countryID+ "'");
        while (rs.next()){
            String firstDiv = rs.getString("Division");
            allDivs.add(firstDiv); //note: this is just adding a list of strings, not FirstDivision objects.
            //to solve this, should I make addCustomerFirstDiv combobox only hold FirstClassDivision objects?
        }
        updateCustomerFirstDiv.setItems(allDivs);

        //set First Division combobox
        FirstClassDivisions selectedDiv = new FirstClassDivisions(divID, Util.firstIDtoDiv(divID), selectedCountry.getCountryID());
        updateCustomerFirstDiv.getSelectionModel().select(selectedDiv.getDivision());
    }
}
