package sample;

import DAO.DBConnection;
import controller.loginScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        /*Locale.setDefault(new Locale("fr"));
        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
        if(!Locale.getDefault().getLanguage().equals("fr")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This is NOT french");
        }
        if(Locale.getDefault().getLanguage().equals("fr")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This is in french");
        }
*/
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginScreen.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void main(String[] args) {
        /*Locale.setDefault(new Locale("fr"));
        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());

        if(!Locale.getDefault().getLanguage().equals("fr")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This is NOT french");
        }
        if(Locale.getDefault().getLanguage().equals("fr")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This is in french");
        }*/
        //ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", new Locale("fr"));
        /*
        if(Locale.getDefault().getLanguage().equals("fr")){
            french = true;
            System.out.println(rb.getString("Submit"));
        }
*/
        DBConnection.openConnection();
        //launch(args); pulls a bunch of methods that start loading your GUIs, so you want to connect to database
        //before this launch method is called, and you want to close database after it is called

        launch(args);
        DBConnection.closeConnection();
    }
}
