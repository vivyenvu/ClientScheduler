package controller;

import DAO.CountryDaoImpl;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateCustomerController implements Initializable {
    public TextField updateCustomerID;
    public TextField updateCustomerName;
    public TextField updateCustomerAddress;
    public TextField updateCustomerPostal;
    public TextField updateCustomerPhone;
    public ComboBox updateCustomerFirstDiv;
    public ComboBox updateCustomerCountry;

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

    public void sendCustomer(Customers customer){
        updateCustomerID.setText(String.valueOf(customer.getCustomerID()));
        updateCustomerName.setText(customer.getCustomerName());
        updateCustomerAddress.setText(customer.getAddress());
        updateCustomerPostal.setText(customer.getPostalCode());
        updateCustomerPhone.setText(customer.getPhone());
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
}
