package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class loginScreenController {
    public void onClickSubmit(ActionEvent actionEvent) throws IOException {
        //add validation for username and password before going to Main Menu
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1080, 400);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
