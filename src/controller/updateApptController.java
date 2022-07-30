package controller;

import DAO.ContactDaoImpl;
import DAO.CustomerDaoImpl;
import DAO.Query;
import DAO.UserDaoImpl;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class updateApptController implements Initializable{
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
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        Contacts selectedContact = null;
        for (Contacts c : Contacts.getAllContacts()){
            if (c.getContactID() == appt.getContactIDFK()){
                selectedContact = c;
            }
        }
        updateApptTitle.setText(appt.getTitle());
        updateApptDescription.setText(appt.getDesc());
        updateApptLocation.setText(appt.getLocation());
        updateApptContact.getSelectionModel().select(selectedContact);
        updateApptType.setText(appt.getType());

        LocalDateTime chooseDate = appt.getStart();
        String formattedDate = chooseDate.format(formatter);
        LocalDate selectedDate = LocalDate.parse(formattedDate);
        updateApptDate.setValue(selectedDate);

        updateApptStartTime.getSelectionModel().select(appt.getStart().toLocalTime());
        updateApptEndTime.getSelectionModel().select(appt.getEnd().toLocalTime());

        Customers selectedCustomer = null;
        for (Customers cust : Customers.getAllCustomers()){
            if (cust.getCustomerID() == appt.getCustomerIDFK()){
                selectedCustomer = cust;
            }
        }
        updateApptCustomerID.getSelectionModel().select(selectedCustomer);

        Users selectedUser = null;
        for (Users u : Users.getAllUsers()){
            if (u.getUserID() == appt.getUserIDFK()){
                selectedUser = u;
            }
        }
        updateApptUserID.getSelectionModel().select(selectedUser);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //calculate time offset between system timezone and utc in hours
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println(currentZone);
        long now = System.currentTimeMillis();
        long diff = TimeZone.getTimeZone("UTC").getOffset(now) - TimeZone.getTimeZone(currentZone).getOffset(now);
        diff = diff/3600000; // turns milliseconds into hours

        //applies offset to start time and populates combobox
        ObservableList<LocalTime> startUTC = Appointments.getStartBizHours();
        ObservableList<LocalTime> startLocal = FXCollections.observableArrayList();

        for (LocalTime time : startUTC){
            LocalTime local = time.minusHours(diff);
            startLocal.add(local);
        }
        updateApptStartTime.setItems(startLocal);
        updateApptStartTime.setVisibleRowCount(5);

        //applies offset to end time and populates combobox
        ObservableList<LocalTime> endUTC = Appointments.getEndBizHours();
        ObservableList<LocalTime> endLocal = FXCollections.observableArrayList();

        for (LocalTime time : endUTC){
            LocalTime local = time.minusHours(diff);
            endLocal.add(local);
        }
        updateApptEndTime.setItems(endLocal);
        updateApptEndTime.setVisibleRowCount(5);

        //populates Contacts combobox
        ObservableList<Contacts> contactDisplay = null;
        try {
            contactDisplay = ContactDaoImpl.getAllContacts();
            updateApptContact.setItems(contactDisplay);
            updateApptContact.setVisibleRowCount(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //populates Customer ID combobox
        ObservableList<Customers> customerDisplay = null;
        try {
            customerDisplay = CustomerDaoImpl.getAllCustomers();
            updateApptCustomerID.setItems(customerDisplay);
            updateApptCustomerID.setVisibleRowCount(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //populates Contacts ID combobox
        ObservableList<Users> userDisplay = null;
        try {
            userDisplay = UserDaoImpl.getAllUsers();
            updateApptUserID.setItems(userDisplay);
            updateApptUserID.setVisibleRowCount(5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setAllContacts()
        try {
            ObservableList<Contacts> loadContacts = ContactDaoImpl.getAllContacts();
            Contacts.setAllContacts(loadContacts);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
