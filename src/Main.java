import Controllers.LoginWindowController;
import Properties.DataBaseProperties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        String url = "jdbc:mysql://localhost:3306/hairroutine";

        //default
        //String user = "admin";
        //String password = "admin";

        //my localhost
        DataBaseProperties dataBaseProperties = new DataBaseProperties();
        String user = dataBaseProperties.getUserProperty();
        String password = dataBaseProperties.getPasswordProperty();

        Connection dataBaseConnection = DriverManager.getConnection(url,user,password);
       // Statement statement = dataBaseConnection.createStatement();
       // String sqlQuery="   ";//Latter
        //ResultSet resultSet = statement.executeQuery(sqlQuery);

        LoginWindowController loginWindow = new LoginWindowController();


        Stage stage = new Stage();

        stage.setScene(loginWindow.setLoginWindowScene());
        stage.show();






























    }
}
