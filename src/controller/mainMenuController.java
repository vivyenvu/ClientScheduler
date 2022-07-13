package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainMenuController {
    public void onCustomerAddBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onCustomerUpdateBtn(ActionEvent actionEvent) {
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

    public void onApptUpdateBtn(ActionEvent actionEvent) {
    }

    public void onApptDeleteBtn(ActionEvent actionEvent) {
    }

    public void onWeekViewBtn(ActionEvent actionEvent) {
    }

    public void onMonthViewBtn(ActionEvent actionEvent) {
    }
}
