package controller;

import DAO.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class loginScreenController implements Initializable {

    public TextField usernameText;
    public TextField passwordText;
    public Label errorMessageDisplay;
    public Users currentUser;
    public Label usernameLabel;
    public Label passwordLabel;
    public Button submitLabel;
    public Label locationLabel;

    public void onClickSubmit(ActionEvent actionEvent) throws Exception {
        Locale.setDefault(new Locale("fr"));

        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());

        String inputUsername = usernameText.getText();
        String inputPassword = passwordText.getText();
        Users currentUser = UserDaoImpl.getUser(inputUsername);

        try {
            //String associatedPassword = UserDaoImpl.getPassword(inputUsername);
            String associatedPassword = currentUser.getPassword();

            System.out.println("InputPassword is " + inputPassword + " while AssociatedPassword is " + associatedPassword); //REMOVE WHEN DONE TESTING

            if (inputPassword.equals(associatedPassword)) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 800, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
            try {
                if (!inputPassword.equals(associatedPassword)) {
                    try {
                        if (Locale.getDefault().getLanguage().equals("fr")) {
                            errorMessageDisplay.setText(rb.getString("InvalidPassword"));
                        }
                    }
                    catch (MissingResourceException e) {
                        errorMessageDisplay.setText("Invalid password. \n");
                    }
                }
            }
            catch (NullPointerException ex) {
                try {
                    if (Locale.getDefault().getLanguage().equals("fr")) {
                        errorMessageDisplay.setText(rb.getString("InvalidUsername"));
                    }
                }
                catch (MissingResourceException e) {
                    errorMessageDisplay.setText("Invalid username. \n");
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            //Locale.setDefault(new Locale("fr"));
            ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());

            if(Locale.getDefault().getLanguage().equals("fr")){
                usernameLabel.setText(rb.getString("Username"));
                passwordLabel.setText(rb.getString("Password"));
                locationLabel.setText(rb.getString("Location"));
                submitLabel.setText(rb.getString("Submit"));
            }
        }
        catch(MissingResourceException e){
            System.out.println("Resource bundle is missing");
        }
    }
}

