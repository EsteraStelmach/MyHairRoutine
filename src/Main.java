import Properties.DataBaseProperties;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Stage stage = new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");
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

        FXMLLoader fxmlLoader=new FXMLLoader();
        Parent loginWindowBorderPane = fxmlLoader.load(this.getClass().getResource("/fxml/LoginWindow.fxml"),bundle);

        Scene scene = new Scene(loginWindowBorderPane);
        stage.setScene(scene);
        stage.show();

    }
}
