package controller;

import DAO.DBConnection;
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
import java.util.ArrayList;
//DELETE THIS IMPORT LATER
import java.util.List;

public class loginScreenController {
    public TextField usernameText;
    public TextField passwordText;
    public Label locationDisplay;
    public Label errorMessageDisplay;
    //ObservableList<Users> allUsers = FXCollections.ObservableArrayList();

    public void onClickSubmit(ActionEvent actionEvent) throws IOException {
        //add validation for username and password before going to Main Menu
        String inputUsername = usernameText.getText();
        String inputPassword = passwordText.getText();

        //TEMPORARY List UNTIL I SET UP MODELS
        List<String> users = new ArrayList<>();

        try {
            String sql = "SELECT User_Name FROM users"; //ALSO SELECT PASSWORD AFTER I SET UP MODELS
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            /*rest of the code where you populate ObservableList<Users> allUsers with every
            row from the table, and then a for loop to validate username and passwords
            match before proceeding to the Main Menu
             */

            while (rs.next()) {
                String userLoaded = rs.getString("User_Name");
                users.add(userLoaded);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

            if (users.contains(inputUsername)) {
                //DELETE THIS PRINT LATER
                System.out.println ("Username is valid");
                Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 800, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
        }

    }

