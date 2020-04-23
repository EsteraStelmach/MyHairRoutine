import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Locale.setDefault(new Locale("pl"));
        //Locale.setDefault(new Locale("nl"));
        Locale.setDefault(new Locale("nl"));

        Stage stage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");

        FXMLLoader fxmlLoader=new FXMLLoader();
        Parent loginWindowBorderPane = fxmlLoader.load(this.getClass().getResource("/fxml/LoginWindow.fxml"),bundle);

        Scene scene = new Scene(loginWindowBorderPane);
        stage.setScene(scene);
        stage.show();

    }
}
