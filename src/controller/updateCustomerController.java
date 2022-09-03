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
import model.Countries;
import model.Customers;
import model.FirstClassDivisions;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * User can update existing customer and associated information.
 */
public class updateCustomerController implements Initializable {
    public TextField updateCustomerID;
    public TextField updateCustomerName;
    public TextField updateCustomerAddress;
    public TextField updateCustomerPostal;
    public TextField updateCustomerPhone;
    public ComboBox updateCustomerFirstDiv;
    public ComboBox <Countries> updateCustomerCountry;

    /**
     * When save button is clicked, this method will validate that all fields are not blank and valid.
     * If there is invalid data, an error will pop up. Otherwise, a customer is updated in the database.
     * @param actionEvent when Save button is clicked
     * @throws IOException
     * @throws SQLException
     */
    public void onClickUpdateCustomerSaveBtn(ActionEvent actionEvent) throws IOException, SQLException {
        String errorMessages = "";
        int custID = Integer.parseInt(updateCustomerID.getText());
        String custName = updateCustomerName.getText();
        String custPostal = updateCustomerPostal.getText();
        String custPhone = updateCustomerPhone.getText();
        String custAddress = updateCustomerAddress.getText();
        String custDivision = (String) updateCustomerFirstDiv.getValue();

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
        else {
            int custDivID = Util.firstDivToID(custDivision);
            CustomerDaoImpl.updateCustomer(custID, custName, custAddress, custPostal, custPhone, custDivID);

            Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 700);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Directs user back to the Main Menu.
     * @param actionEvent Cancel button is clicked
     * @throws IOException
     */
    public void onUpdateCustomerCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Auto-populates fields and combo boxes with information based on which Customer was selected on the Main Menu.
     * @param customer selected customer
     * @throws SQLException
     */
    public void sendCustomer(Customers customer) throws SQLException {
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

    /**
     * Populates Country combo box
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Countries> allCountries = CountryDaoImpl.getAllCountries();
            updateCustomerCountry.setItems(allCountries);
            updateCustomerCountry.setVisibleRowCount(5);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Populates first level division combo box based off country of selected customer
     * @param actionEvent
     * @throws SQLException
     */
    public void onUpdateCustomerCountry(ActionEvent actionEvent) throws SQLException {
        ObservableList<String> allDivs= FXCollections.observableArrayList();
        Countries selectedCountry = updateCustomerCountry.getSelectionModel().getSelectedItem(); //eg Canada

        int countryID = Util.countryToCountryID(selectedCountry.getCountry());
        ResultSet rs = Query.getRS ("SELECT Division FROM first_level_divisions WHERE Country_ID = '" +countryID+ "'");
        while (rs.next()){
            String firstDiv = rs.getString("Division");
            allDivs.add(firstDiv);
        }
        updateCustomerFirstDiv.setItems(allDivs);
    }
}
