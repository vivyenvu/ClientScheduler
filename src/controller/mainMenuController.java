package controller;

import DAO.CustomerDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {
    public TableView customerTable;
    public TableColumn customerTableID;
    public TableColumn customerTableName;
    public TableColumn customerTableAddress;
    public TableColumn customerTablePostal;
    public TableColumn customerTablePhone;
    public TableColumn customerTableCountry;
    public TableColumn customerTableFirst;
    public TableView allApptTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerTable.setItems(CustomerDaoImpl.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        customerTableID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
    public void onCustomerAddBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onCustomerUpdateBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updateCustomer.fxml"));
        loader.load();

        /* on old project
        modPart = (Part) mainPartTable.getSelectionModel().getSelectedItem();
        modPartIndex = getAllParts().indexOf(modPart);
        FIGURE OUT WHAT GLOBAL VARIABLES I NEED TO UPDATE CUSTOMER INFO ON NEXT SCREEN
         */

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setTitle("Update Customer");
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void onCustomerDeleteBtn(ActionEvent actionEvent) {
    }

    public void onApptAddBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppt.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void onApptUpdateBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updateAppt.fxml"));
        loader.load();

        /* on old project
        modPart = (Part) mainPartTable.getSelectionModel().getSelectedItem();
        modPartIndex = getAllParts().indexOf(modPart);
        FIGURE OUT WHAT GLOBAL VARIABLES I NEED TO UPDATE CUSTOMER INFO ON NEXT SCREEN
         */

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setTitle("Update Appointment");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onApptDeleteBtn(ActionEvent actionEvent) {
    }

    public void onWeekViewBtn(ActionEvent actionEvent) {
    }

    public void onMonthViewBtn(ActionEvent actionEvent) {
    }

    public void onClickReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }


}
