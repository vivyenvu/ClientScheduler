package controller;

import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.Countries;
import model.FirstClassDivisions;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addCustomerController implements Initializable {
    public TextField addCustomerID;
    public TextField addCustomerName;
    public TextField addCustomerAddress;
    public TextField addCustomerPostal;
    public TextField addCustomerPhone;
    public ComboBox addCustomerFirstDiv;
    public ComboBox <Countries> addCustomerCountry;
    private ObservableList<Appointments> associatedAppts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Countries> allCountries = CountryDaoImpl.getAllCountries();
            addCustomerCountry.setItems(allCountries);
            addCustomerCountry.setVisibleRowCount(5);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void onAddCustomerSaveBtn(ActionEvent actionEvent) throws SQLException, IOException {
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
            Scene scene = new Scene(root, 1000, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onAddCustomerCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onAddCustomerCountry(ActionEvent actionEvent) throws SQLException {
        ObservableList<String> allDivs= FXCollections.observableArrayList();
        Countries selectedCountry = addCustomerCountry.getSelectionModel().getSelectedItem(); //eg Canada

        int countryID = Util.countryToCountryID(selectedCountry.getCountry()); //DOES THE HELPER NEED TO BE STATIC
        ResultSet rs = Query.getRS ("SELECT Division FROM first_level_divisions WHERE Country_ID = '" +countryID+ "'");
        while (rs.next()){
            String firstDiv = rs.getString("Division");
            allDivs.add(firstDiv); //note: this is just adding a list of strings, not FirstDivision objects.
            //to solve this, should I make addCustomerFirstDiv combobox only hold FirstClassDivision objects?
        }
        addCustomerFirstDiv.setItems(allDivs);
    }
}
