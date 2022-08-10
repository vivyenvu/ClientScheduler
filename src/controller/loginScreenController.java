package controller;

import DAO.UserDaoImpl;
import helper.Util;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.Users;

import java.net.URL;
import java.time.LocalTime;
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
    public boolean isFrench = false;
    public String associatedPassword;
    public Label countryDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            //Locale.setDefault(new Locale("fr", "FR"));
            ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
            countryDisplay.setText(Locale.getDefault().getDisplayCountry());

            if(Locale.getDefault().getLanguage().equals("fr")){
                locationLabel.setText(rb.getString("Location"));
                usernameLabel.setText(rb.getString("Username"));
                passwordLabel.setText(rb.getString("Password"));
                submitLabel.setText(rb.getString("Submit"));
            }

        }
        catch(MissingResourceException e){
            System.out.println("Resource bundle is missing");
        }
    }

    public void onClickSubmit(ActionEvent actionEvent){
        //Locale.setDefault(new Locale("fr"));

        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")) {
            isFrench = true;
        }

        String inputUsername = usernameText.getText();
        String inputPassword = passwordText.getText();

        try {
            currentUser = UserDaoImpl.getUser(inputUsername);
            associatedPassword = currentUser.getPassword();
            //System.out.println("InputPassword is " + inputPassword + " while AssociatedPassword is " + associatedPassword);

            if (inputPassword.equals(associatedPassword)) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(Util.upcomingMessage(currentUser)); //MADE STATIC BUT CHECK IF THIS MESSES ANYTHING UP
                alert.show();
            }
            if (!inputPassword.equals(associatedPassword)) {
                if (isFrench) {
                    errorMessageDisplay.setText(rb.getString("InvalidPassword"));
                } else {
                    errorMessageDisplay.setText("Invalid password. \n");
                }
            }
        }
        catch (NullPointerException e) {
            if (isFrench) {
                errorMessageDisplay.setText(rb.getString("InvalidUsername"));
            } else {
                errorMessageDisplay.setText("Invalid username. \n");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

