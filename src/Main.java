import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage stage = new Stage();

        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/fxml/LoginWindow.fxml"));
        Parent loginWindowBorderPane = fxmlLoader.load();

        Scene scene = new Scene(loginWindowBorderPane);
        stage.setScene(scene);
        stage.show();

    }
}
