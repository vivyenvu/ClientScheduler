package controller;

import DAO.DBConnection;
import DAO.Query;
import DAO.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//DELETE THIS IMPORT LATER
import java.util.List;

public class loginScreenController {
    public TextField usernameText;
    public TextField passwordText;
    public Label locationDisplay;
    public Label errorMessageDisplay;
    public Users currentUser;
    //ObservableList<Users> allUsers = FXCollections.ObservableArrayList();

    public void onClickSubmit(ActionEvent actionEvent) throws Exception {
        String inputUsername = usernameText.getText();
        String inputPassword = passwordText.getText();
        try {
            String associatedPassword = UserDaoImpl.getPassword(inputUsername);
            if (inputPassword.equals(associatedPassword)) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 800, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
            else {
                System.out.println("InputPassword is "+inputPassword + " while AssociatedPassword is " +associatedPassword);
                //DO I NEED A WARNING TO POP UP SOMEHWERE ELSE?
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

