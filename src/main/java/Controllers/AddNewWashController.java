package Controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddNewWashController {

    @FXML
    private Button addNewWash;
    @FXML
    private TextArea newWashType;
    @FXML
    private TextArea newWashingDescription;

    private static String washType;
    private static String washingDescription = " ";

    private BooleanProperty removeProductButtonProperty = new SimpleBooleanProperty(false);
    private StringProperty washTypeProperty = new SimpleStringProperty();

    @FXML
    public void initialize() {
        newWashType.textProperty().bindBidirectional(washTypeProperty);
        addNewWash.disableProperty().bindBidirectional(removeProductButtonProperty);
        removeProductButtonProperty.bind(washTypeProperty.isEmpty());
    }

    public void addNewWashRoutine() {
        washingDescription = newWashingDescription.getText();
        washType = newWashType.getText();
        addNewWash.getScene().getWindow().hide();
    }

    public static String getWashType() {
        return washType;
    }

    public static String getWashingDescription() {
        return washingDescription;
    }
}
