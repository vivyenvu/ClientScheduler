package controller;

import DAO.ApptDaoImpl;
import DAO.ContactDaoImpl;
import DAO.CustomerDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;

import java.io.IOException;
import java.sql.SQLException;

public class reportsController {
    public TextArea displayArea;

    public void onCancelReportsBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onApptTypeBtn(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> allAppointments = ApptDaoImpl.getAllAppts();
        ObservableList<String> allTypes = FXCollections.observableArrayList();

        String allInfo = "Month                        Type                        Count\n";
        //populate allTypes by iterating through allAppointments and looking for unique types
        allTypes.add(allAppointments.get(0).getType());

        for (int i=0; i < allTypes.size(); i++){
            for (Appointments appt : allAppointments){
                if (!appt.getType().equals(allTypes.get(i))){
                    allTypes.add(appt.getType());
                    i=0;
                }
            }
        }
        for (String type : allTypes){
            System.out.println(type+"\n");
        }

        /*for (int i=0; i < allAppointments.size(); i++){
            for (String type : allTypes){ //for (int i=0; i < allTypes.size())
                if (!allAppointments.get(i).getType().equals(type)){
                    allTypes.add(allAppointments.get(i).getType());
                    i=0;
                }
            }
        }*/


        for (String type : allTypes) {
            int count = 0;
            String month = "make lamda function";
            for (Appointments appt : allAppointments) {
                if (appt.getType() == type && appt.getStart().getMonth().equals(month)) { //there might be an issue here if .getMonth doesn't return a string
                    count++;
                }
                allInfo += (month +"                       "+type+"                  "+count+"\n");
            }
        }
        displayArea.setText(allInfo);
    }

    public void onContactScheduleBtn(ActionEvent actionEvent) throws SQLException, IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("/view/reportsSchedule.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 850, 400);
        stage.setTitle("Reports Schedule");
        stage.setScene(scene);
        stage.show();*/
        ObservableList<Contacts> allContacts = ContactDaoImpl.getAllContacts();
        ObservableList<Appointments> allAppointments = ApptDaoImpl.getAllAppts();

        //Button printTextBtn = new Button("Print Text");
        //printTextBtn.setOnAction(e -> print(text));
        //String test = "testing";
        String allInfo = "Appt ID            Title                        Type                        Description                        Start                        End                        Customer ID\n";
        //allInfo += test+ "       " +test;
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

        displayArea.setText("U.S                      UK                      Canada \n" +
                            US+"                          "+UK+"                        "+Canada);
    }
}
