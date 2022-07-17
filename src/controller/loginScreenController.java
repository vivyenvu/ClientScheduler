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
        //add validation for username and password before going to Main Menu
        String inputUsername = usernameText.getText();
        String inputPassword = passwordText.getText();
        //TEMPORARY List UNTIL I SET UP MODELS
        //List<String> users = new ArrayList<>();

        try {
            /*String sql = "SELECT User_Name FROM users"; //ALSO SELECT PASSWORD AFTER I SET UP MODELS
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);*/

            //ResultSet rs = Query.getRS("SELECT User_Name FROM users");

            /*rest of the code where you populate ObservableList<Users> allUsers with every
            row from the table, and then a for loop to validate username and passwords
            match before proceeding to the Main Menu
             */
            String associatedPassword = UserDaoImpl.getPassword(inputUsername);

            if (associatedPassword == inputPassword) {
                System.out.println("This is the correct password");
                Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 800, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
            else {
                System.out.println("No match");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            /*
            while (rs.next()) {
                String userLoaded = rs.getString("User_Name");
                users.add(userLoaded);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (users.contains(inputUsername)) {
                //DELETE THIS PRINT LATER
                System.out.println ("Username is valid");

            }
        }
*/
    }}

