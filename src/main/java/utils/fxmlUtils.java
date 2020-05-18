package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.util.ResourceBundle;

public class fxmlUtils {

    public static Parent fxmlLoader(String path) {

       ResourceBundle bundle = ResourceBundle.getBundle("Bundles.messages");

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent loginWindowBorderPane = null;
        try {
            loginWindowBorderPane = fxmlLoader.load(fxmlUtils.class.getResource(path),bundle);
        } catch (Exception e) {
            DialogsUtils.errorDialogLoadingFXMLFiles(e);

        }
        return loginWindowBorderPane;
    }
}
