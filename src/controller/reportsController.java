package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Contacts;
import model.Customers;

import java.io.IOException;

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

    public void onApptTypeBtn(ActionEvent actionEvent) {
    }

    public void onContactScheduleBtn(ActionEvent actionEvent) {
        ObservableList<Contacts> allContacts = Contacts.getAllContacts();
        String [] contactInfo = new String[allContacts.size()];
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
