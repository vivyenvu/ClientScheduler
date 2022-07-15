package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginScreenController {
    public TextField usernameText;
    public TextField passwordText;
    public Label locationDisplay;
    public Label errorMessageDisplay;

    public void onClickSubmit(ActionEvent actionEvent) throws IOException {
        //add validation for username and password before going to Main Menu
        String inputUsername = usernameText.getText();
        String inputPassword = passwordText.getText();

        try {
            String sql = "SELECT User_Name, Password FROM users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 700);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
