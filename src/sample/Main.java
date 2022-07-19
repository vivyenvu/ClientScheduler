package sample;

import DAO.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginScreen.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Boolean isFrench = false;

        Locale.setDefault(new Locale("fr"));
        ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());

        //ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", new Locale("fr"));
        if(Locale.getDefault().getLanguage().equals("fr")){
            isFrench = true;
            System.out.println(rb.getString("Submit"));
        }

        DBConnection.openConnection();
        //launch(args); pulls a bunch of methods that start loading your GUIs, so you want to connect to database
        //before this launch method is called, and you want to close database after it is called

        launch(args);
        DBConnection.closeConnection();
    }
}
