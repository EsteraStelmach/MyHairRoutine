import Controllers.LoginWindowController;
import Properties.DataBaseProperties;
import Properties.RegisterWindowProperties;
import dataBase.DataBaseConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DialogsUtils;

import java.sql.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        DataBaseConnection dataBaseConnection = new DataBaseConnection();

        dataBaseConnection.connect();



        LoginWindowController loginWindow = new LoginWindowController();

        Stage stage = new Stage();

        stage.setScene(loginWindow.setLoginWindowScene());
        stage.show();

































    }
}
