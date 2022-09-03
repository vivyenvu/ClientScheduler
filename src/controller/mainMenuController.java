package controller;

import DAO.ApptDaoImpl;
import DAO.CustomerDaoImpl;
import helper.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Appointments;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * Main Menu allows users to view all Customers and Appointments. User can add, update, or delete
 * any customer or appointment. Appointment view can be altered to include all appointments,
 * week view, or month view.
 */
public class mainMenuController implements Initializable {
    public TableView <Customers> customerTable;
    public TableColumn customerTableID;
    public TableColumn customerTableName;
    public TableColumn customerTableAddress;
    public TableColumn customerTablePostal;
    public TableColumn customerTablePhone;
    public TableColumn customerTableCountry;
    public TableColumn customerTableFirst;
    public TableView <Appointments> allApptTable;
    public TableColumn apptTableID;
    public TableColumn apptTableTitle;
    public TableColumn apptTableDesc;
    public TableColumn apptTableLoc;
    public TableColumn apptTableType;
    public TableColumn apptTableStart;
    public TableColumn apptTableEnd;
    public TableColumn apptTableCustID;
    public TableColumn apptTableUserID;
    public TableColumn apptTableContact;

    /**
     * Populates Customers and Appointments TableViews.
     * @param url
     * @param resourceBundle
     */
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
        customerTableCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerTableFirst.setCellValueFactory(new PropertyValueFactory<>("divisionIDFK"));

        //appointments table
        try {
            allApptTable.setItems(ApptDaoImpl.getAllAppts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        apptTableID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptTableTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptTableDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        apptTableLoc.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTableContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        apptTableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptTableStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptTableEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptTableCustID.setCellValueFactory(new PropertyValueFactory<>("customerIDFK"));
        apptTableUserID.setCellValueFactory(new PropertyValueFactory<>("userIDFK"));

    }

    /**
     * Directs user to Add Customer scene.
     * @param actionEvent user clicks Add customer button
     * @throws IOException
     */
    public void onCustomerAddBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Directs user to Update Customer scene.
     * @param actionEvent user clicks Update customer button
     * @throws IOException
     * @throws SQLException
     */
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

    /**
     * Deletes selected customer from database and TableView. If no customer is selected,
     * then an error pops up. If the customer still has associated appointments,
     * an error pops up. Otherwise, a pop up details which customer was successfully deleted.
     * @param actionEvent
     * @throws SQLException
     */
    public void onCustomerDeleteBtn(ActionEvent actionEvent) throws SQLException {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete customer?");
            alert.setContentText("Are you sure you want to delete this customer? ");

            Optional<ButtonType> result = alert.showAndWait();

            Customers forDeletion = customerTable.getSelectionModel().getSelectedItem();
            int idForDeletion = forDeletion.getCustomerID();

            if (result.isPresent() && result.get() == ButtonType.OK){
                if(Customers.checkForAppts(idForDeletion) != null){
                    Util.stringToAlert("Remove this customer's appointments before attempting to delete customer. ");
                }

                else {
                    CustomerDaoImpl.deleteCustomer(idForDeletion);
                    customerTable.setItems(CustomerDaoImpl.getAllCustomers());
                    Util.stringToAlert("Customer with ID " + idForDeletion + " has been deleted.");
                }
            }
        }
        catch(NullPointerException e){
            Util.stringToAlert("Can't delete because no customer was selected. ");
        }
    }

    /**
     * Directs user to Add Appointment Scene.
     * @param actionEvent user clicks Add appointment button
     * @throws IOException
     */
    public void onApptAddBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addAppt.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Directs user to Update Appointment scene. Error pops up if user has not selected an appointment.
     * @param actionEvent
     * @throws IOException
     */
    public void onApptUpdateBtn(ActionEvent actionEvent) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateAppt.fxml"));
            loader.load();

            Appointments updateAppt = allApptTable.getSelectionModel().getSelectedItem();

            updateApptController updateApptCtrl = loader.getController();
            updateApptCtrl.sendAppointment(updateAppt);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Update Appointment");
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException | SQLException e) {
            Util.stringToAlert("Please select an appointment. ");
        }
    }

    /**
     * Asks user to confirm that they way to delete this appointment.
     * If so, deletes selected appointment. Error pops up if user had not selected an appointment.
     * Confirmation message pops up after successful apointment deletion.
     * @param actionEvent
     */
    public void onApptDeleteBtn(ActionEvent actionEvent) {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete appointment?");
            alert.setContentText("Are you sure you want to delete this appointment? ");

            Optional<ButtonType> result = alert.showAndWait();

            Appointments forDeletion = allApptTable.getSelectionModel().getSelectedItem();
            int idForDeletion = forDeletion.getApptID();
            String apptType = forDeletion.getType();

            if (result.isPresent() && result.get() == ButtonType.OK){
                ApptDaoImpl.deleteAppt(idForDeletion);
                allApptTable.setItems(ApptDaoImpl.getAllAppts());
                Util.stringToAlert("Appointment with ID "+idForDeletion+ " and Type "+apptType+" has been deleted.");
            }

        }
        catch(NullPointerException | SQLException e){
            Util.stringToAlert("Can't delete because no customer was selected. ");
        }
    }

    /**
     * Appointments TableView only shows appointments occurring this week.
     * @param actionEvent Week View radio button is selected
     * @throws SQLException
     */
    public void onWeekViewBtn(ActionEvent actionEvent) throws SQLException {
        allApptTable.setItems(ApptDaoImpl.getWeekAppts());
    }

    /**
     * Appointments TableView only shows appointments occuring this month.
     * @param actionEvent Month View radio button is selected
     * @throws SQLException
     */
    public void onMonthViewBtn(ActionEvent actionEvent) throws SQLException {
        allApptTable.setItems(ApptDaoImpl.getMonthAppts());
    }

    /**
     * Directs user to Reports scene.
     * @param actionEvent Reports button is clicked
     * @throws IOException
     */
    public void onClickReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 850, 400);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Appointments TableView displays all appointments.
     * @param actionEvent All radio button is selected.
     * @throws SQLException
     */
    public void onAllViewBtn(ActionEvent actionEvent) throws SQLException {
        allApptTable.setItems(ApptDaoImpl.getAllAppts());
    }
}
