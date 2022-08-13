package controller;

import DAO.ApptDaoImpl;
import DAO.ContactDaoImpl;
import DAO.CustomerDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;
import model.Contacts;

import java.sql.SQLException;

public class reportsScheduleController {
    public TableView displaySchedule;
    public TableColumn tableApptID;
    public TableColumn tableTitle;
    public TableColumn tableType;
    public TableColumn tableDesc;
    public TableColumn tableStart;
    public TableColumn tableEnd;
    public TableColumn tableCustID;

    public void onCancelReportsBtn(ActionEvent actionEvent) {
    }

    public void onApptTypeBtn(ActionEvent actionEvent) {
    }

    public void onContactScheduleBtn(ActionEvent actionEvent) throws SQLException {
        ObservableList<Contacts> allContacts = ContactDaoImpl.getAllContacts();
        ObservableList<Appointments> allAppointments = ApptDaoImpl.getAllAppts();
        ObservableList<Appointments> allInfo = FXCollections.observableArrayList();
    /*
        for (Contacts contact : allContacts) {
            //allInfo.add(contact);
            for (Appointments appt : allAppointments) {
                if (appt.getContactIDFK() == contact.getContactID()) {
                    allInfo.add(appt);
                    /*String apptID = String.valueOf(appt.getApptID());
                    String title = appt.getTitle();
                    String type = appt.getType();
                    String desc = appt.getDesc();
                    String start = appt.getStart().toString();
                    String end = appt.getEnd().toString();
                    String custID = String.valueOf(appt.getCustomerIDFK());
                }
            }
        }*/

        allInfo.add(allAppointments.get(1));
        displaySchedule.setItems(allInfo);

        tableApptID.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        tableTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tableStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        tableEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        tableCustID.setCellValueFactory(new PropertyValueFactory<>("custIDFK"));
    }

    public void onCustomerPerCountryBtn(ActionEvent actionEvent) {
    }
}
