package controller;

import DAO.CountryDaoImpl;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateCustomerController implements Initializable {
    public TextField updateCustomerID;
    public TextField updateCustomerName;
    public TextField updateCustomerAddress;
    public TextField updateCustomerPostal;
    public TextField updateCustomerPhone;
    public ComboBox updateCustomerFirstDiv;
    public ComboBox <Countries> updateCustomerCountry;

    public void onClickUpdateCustomerSaveBtn(ActionEvent actionEvent) {
    }

    public void onUpdateCustomerCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void sendCustomer(Customers customer) throws SQLException {
        updateCustomerID.setText(String.valueOf(customer.getCustomerID()));
        updateCustomerName.setText(customer.getCustomerName());
        updateCustomerAddress.setText(customer.getAddress());
        updateCustomerPostal.setText(customer.getPostalCode());
        updateCustomerPhone.setText(customer.getPhone());
        updateCustomerCountry.getSelectionModel().select
                //(Util.divIDToCountry(customer.getDivisionIDFK()));
        updateCustomerFirstDiv.getSelectionModel().select(customer.getDivisionIDFK());
    }

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

    //might have to move body of this to initialize or sendCustomer
    public void onUpdateCustomerCountry(ActionEvent actionEvent) throws SQLException {
        ObservableList<String> allDivs= FXCollections.observableArrayList();
        Countries selectedCountry = updateCustomerCountry.getSelectionModel().getSelectedItem(); //eg Canada

        int countryID = Util.countryToCountryID(selectedCountry.getCountry()); //DOES THE HELPER NEED TO BE STATIC
        ResultSet rs = Query.getRS ("SELECT Division FROM first_level_divisions WHERE Country_ID = '" +countryID+ "'");
        while (rs.next()){
            String firstDiv = rs.getString("Division");
            allDivs.add(firstDiv); //note: this is just adding a list of strings, not FirstDivision objects.
            //to solve this, should I make addCustomerFirstDiv combobox only hold FirstClassDivision objects?
        }
        updateCustomerFirstDiv.setItems(allDivs);
    }
}
