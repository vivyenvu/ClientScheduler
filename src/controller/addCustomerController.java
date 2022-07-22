package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addCustomerController {
    public TextField addCustomerID;
    public TextField addCustomerName;
    public TextField addCustomerAddress;
    public TextField addCustomerPostal;
    public TextField addCustomerPhone;
    public ComboBox addCustomerFirstDiv;
    public ComboBox addCustomerCountry;

    public void onAddCustomerSaveBtn(ActionEvent actionEvent) {
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
