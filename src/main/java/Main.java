import Controllers.LoginWindowController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Stage stage = LoginWindowController.getWindow();
        stage.setScene(LoginWindowController.setLoginWindowScene());
        stage.show();



































    }
}
