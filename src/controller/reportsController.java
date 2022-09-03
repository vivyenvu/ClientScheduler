package controller;

import DAO.ApptDaoImpl;
import DAO.ContactDaoImpl;
import LEXMain.GeneralInterface;
import LEXMain.MonthInterface;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Month;

/**
 * Area to display three kinds of reports.
 */
public class reportsController {
    public TextArea displayArea;

    /**
     * Directs user back to Main Menu.
     * @param actionEvent Cancel button is clicked.
     * @throws IOException
     */
    public void onCancelReportsBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays report for total number of appointments by type and month.
     * <p><b>
     * This lambda expression takes an integer and returns the name of the associated month.
     * This makes the output schedule more human-readable.
     * </b></p>
     * @param actionEvent Appt by Type and Month button is clicked
     * @throws SQLException
     */
    public void onApptTypeBtn(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> allAppointments = ApptDaoImpl.getAllAppts();
        ObservableList<String> allTypes = ApptDaoImpl.getAllTypes();

        String allInfo = "Month                                                Type                                                Count\n";
        for (int i=1; i<=12; i++){
        int count = 0;
            for (String type : allTypes) {
                int month = i;
                for (Appointments appt : allAppointments) {
                    count = ApptDaoImpl.getCount(type, month);
                }

                MonthInterface name = m -> String.valueOf(Month.of(m));
                String monthName = name.getMonthName(month);

                if (count != 0){
                    allInfo += (monthName +"                                "+type+"                                                "+count+"\n");
                }
            }
        }
        displayArea.setText(allInfo);
    }

    /**
     * Displays report for schedule of each contact, including their appointment id, title, type, description,
     * start time/date, end time/date, and customer id.
     * @param actionEvent Contact Schedules button is clicked
     * @throws SQLException
     * @throws IOException
     */
    public void onContactScheduleBtn(ActionEvent actionEvent) throws SQLException, IOException {
        ObservableList<Contacts> allContacts = ContactDaoImpl.getAllContacts();
        ObservableList<Appointments> allAppointments = ApptDaoImpl.getAllAppts();
        String allInfo = "Appt ID            Title                        Type                        Description                        Start                        End                        Customer ID\n";

        for (Contacts contact : allContacts) {
            allInfo += contact.getContactName()+"\n";
            for (Appointments appt : allAppointments) {
                if (appt.getContactIDFK() == contact.getContactID()) {
                    String apptID = String.valueOf(appt.getApptID());
                    String title = appt.getTitle();
                    String type = appt.getType();
                    String desc = appt.getDesc();
                    String start = appt.getStart().toString();
                    String end = appt.getEnd().toString();
                    String custID = String.valueOf(appt.getCustomerIDFK());
                    allInfo += (apptID +"                       "+title+"                  "+type+"                  "+desc+"                  "+start+"                  "+end+"                  "+custID +"\n");
                }
            }
        }
        displayArea.setText(allInfo);

    }

    /**
     * Displays report of how many customers are from each country.
     * <p><b>
     * This lambda expression creates a set amount of spaces in between the columns to create
     * a consistent, clean-looking display.
     * </b></p>
     * @param actionEvent Customers per Country button is clicked
     */
    public void onCustomerPerCountryBtn(ActionEvent actionEvent) {
        ObservableList<Customers> allCustomers = Customers.getAllCustomers();
        int US = 0;
        int UK = 0;
        int Canada = 0;

        for (Customers cust : allCustomers)
        {
            if (cust.getDivisionIDFK() >=1 && cust.getDivisionIDFK() <=54){
                US++;
            }
            else if (cust.getDivisionIDFK() >=101 && cust.getDivisionIDFK() <=104){
                UK++;
            }
            else if (cust.getDivisionIDFK() >=60 && cust.getDivisionIDFK() <=72){
                Canada++;
            }
        }
        GeneralInterface space = (s, t) -> s+"                      "+t;
        displayArea.setText(space.addSpace(space.addSpace("U.S","UK"),"Canada")+ "\n"+
                US+"                          "+UK+"                        "+Canada);
    }
}
