package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {

    private static ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");


    public static void userLoginOrPasswordIsNotCorrect(){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("loginWindow.incorrectLoginOrPassword.title"));
        informationAlert.setHeaderText(bundle.getString("loginWindow.incorrectLoginOrPassword.message"));
        informationAlert.show();

    }

    public static Optional<ButtonType> exitApplication(){
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(bundle.getString("loginWindow.exit.title"));
        confirmationAlert.setHeaderText(bundle.getString("loginWindow.exit.message"));
        Optional<ButtonType> result  = confirmationAlert.showAndWait();
        return result;
    }

    public static void registerPasswordAreNotTheSame(){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("registerWindow.incorrectPasswords.title"));
        informationAlert.setHeaderText(null);
        informationAlert.setContentText(bundle.getString("registerWindow.incorrectPasswords.message"));
        informationAlert.show();

    }

    public static void errorDialogLoadingFXMLFiles(Exception error){
        Alert errorAlert = new Alert (Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.FXMLLoader.title"));
        errorAlert.setHeaderText(bundle.getString(error.getMessage()));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        error.printStackTrace(pw);
        pw.close();

        TextArea textArea = new TextArea(sw.toString());
        textArea.setEditable(false);

        textArea.setMaxHeight(Double.MAX_VALUE);
        textArea.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane.setVgrow(textArea,Priority.ALWAYS);

        GridPane root = new GridPane();
        root.setVisible(false);
        root.setMaxWidth(Double.MAX_VALUE);
        root.add(textArea, 0,1);

        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.show();

    }

    public static void errorDialogConnectingToDataBase(String error){
        Alert errorAlert = new Alert (Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.FXMLLoader.title"));
        errorAlert.setHeaderText(bundle.getString("error.FXMLLoader.message"));

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();

    }



















}
