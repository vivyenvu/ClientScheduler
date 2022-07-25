package controller;

import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
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

public class addCustomerController implements Initializable {
    public TextField addCustomerID;
    public TextField addCustomerName;
    public TextField addCustomerAddress;
    public TextField addCustomerPostal;
    public TextField addCustomerPhone;
    public ComboBox addCustomerFirstDiv;
    public ComboBox addCustomerCountry;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Countries> allCountries = CountryDaoImpl.getAllCountries();
            addCustomerCountry.setItems(allCountries);
            addCustomerCountry.setVisibleRowCount(5);
        }
        catch (SQLException throwables) {
            System.out.println("Struggling to load combo box");
            throwables.printStackTrace();
        }

    }

    public void onAddCustomerSaveBtn(ActionEvent actionEvent) throws SQLException {
        String custName = addCustomerName.getText();
        String custPostal = addCustomerPostal.getText();
        String custPhone = addCustomerPhone.getText();
        String custAddress = addCustomerAddress.getText();
        //int custDivID = addCustomerFirstDiv.getValue(); //helper method
        int custDivID = 999;
        CustomerDaoImpl.addCustomer(custName, custAddress, custPostal, custPhone, custDivID); //add customer to the database with Kinkead's webinars
    }

    public void onAddCustomerCancelBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
