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

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * First scene where user inputs username and password. Validates if that combination is found
 * in the database. If time zone is in a french region, scene will be translation to french.
 * Error message if invalid username or password is entered. Every attempt will be logged into
 * login_activity.txt
 */
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
    public String inputUsername;

    /**
     * Detects the current Locale and sets the page to French if in a French region.
     * Otherwise, the page is set to English.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            //Locale.setDefault(new Locale("fr", "FR"));
            ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
            countryDisplay.setText(String.valueOf(ZoneId.systemDefault()));

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

    /**
     * If username and password are correct and found in the database, page is redirected to Main Menu.
     * Otherwise, an error message will appear. Language is also set according to the region/locale detected.
     * @param actionEvent user clicks Submit button
     */
    public void onClickSubmit(ActionEvent actionEvent){
        //Locale.setDefault(new Locale("fr"));

        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")) {
            isFrench = true;
        }

        inputUsername = usernameText.getText();
        String inputPassword = passwordText.getText();

        try {
            currentUser = UserDaoImpl.getUser(inputUsername);
            associatedPassword = currentUser.getPassword();
            //System.out.println("InputPassword is " + inputPassword + " while AssociatedPassword is " + associatedPassword);

            if (inputPassword.equals(associatedPassword)) {
                validLogin();
                Parent root = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 700);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(Util.upcomingMessage(currentUser));
                alert.show();
            }
            if (!inputPassword.equals(associatedPassword)) {
                invalidLogin();
                if (isFrench) {
                    errorMessageDisplay.setText(rb.getString("InvalidPassword"));
                } else {
                    errorMessageDisplay.setText("Invalid password. \n");
                }
            }
        }
        catch (NullPointerException e) {
            invalidLogin();
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

    /**
     * Writes a new log into login_activity.txt when a user enters valid login credentials.
     */
    private void validLogin() {
        try {
            FileWriter fwriter = new FileWriter("src/login_activity.txt", true);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = LocalDateTime.now().format(format);
            fwriter.write("User "+inputUsername+ " successfully logged in at "+time+"\n");
            fwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a new log into login_activity.txt when a user enters invalid login credentials.
     */
    private void invalidLogin() {
        try {
            FileWriter fwriter = new FileWriter("src/login_activity.txt", true);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = LocalDateTime.now().format(format);
            fwriter.write("User "+inputUsername+ " gave invalid login at "+time+"\n");
            fwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

