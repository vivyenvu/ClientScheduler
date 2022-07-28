package controller;

import DAO.CustomerDaoImpl;
import helper.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
        customerTableName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTableAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerTablePostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerTablePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        /*customerTableCountry.setCellValueFactory(new PropertyValueFactory<>("country"));*/
        customerTableFirst.setCellValueFactory(new PropertyValueFactory<>("divisionIDFK"));

        //customerTableCountry.getColumns().add(new TableColumn<String, String>("Country"));

    }
    public void onCustomerAddBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onCustomerUpdateBtn(ActionEvent actionEvent) throws IOException, SQLException {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateCustomer.fxml"));
            loader.load();

            Customers updateCustomer = (Customers) customerTable.getSelectionModel().getSelectedItem();

            updateCustomerController updateCustCtrl = loader.getController();
            updateCustCtrl.sendCustomer(updateCustomer);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Update Customer");
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Util.stringToAlert("Please select a customer. ");
        }
    }

    public void onCustomerDeleteBtn(ActionEvent actionEvent) throws SQLException {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete customer?");
            alert.setContentText("Are you sure you want to delete this customer? ");

            Optional<ButtonType> result = alert.showAndWait();

            Object forDeletion = customerTable.getSelectionModel().getSelectedItem();
            int idForDeletion = Integer.parseInt(forDeletion.toString());

            if (result.isPresent() && result.get() == ButtonType.OK){
                CustomerDaoImpl.deleteCustomer(idForDeletion);
                customerTable.setItems(CustomerDaoImpl.getAllCustomers());
                Util.stringToAlert("Customer with ID "+idForDeletion+ " has been deleted.");
            }

        }
        catch(NullPointerException e){
            Util.stringToAlert("Can't delete because no customer was selected. ");
        }
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
