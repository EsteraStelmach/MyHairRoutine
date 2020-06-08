import Controllers.LoginWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {

        //Locale.setDefault(new Locale("en"));


        Stage stage = LoginWindowController.getWindow();
        stage.setScene(LoginWindowController.setLoginWindowScene());
        stage.show();



































    }
}
