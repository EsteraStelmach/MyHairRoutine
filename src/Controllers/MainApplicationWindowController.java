package Controllers;

import dataBase.UserUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.DialogsUtils;
import utils.fxmlUtils;

import java.util.Optional;

public class MainApplicationWindowController {

    private String loginWindowFxmlName = "/fxml/LoginWindow.fxml";

    @FXML
    private Button logOutButton;
    @FXML
    private Label porosityLabel;
    @FXML
    private Label twistTypeLabel;

    @FXML
    public void initialize(){

        porosityLabel.setText(UserUtils.getHairPorosity());
        twistTypeLabel.setText(UserUtils.getHairTwistType());
    }

    public void logOutToLoginWindow() {
        DialogsUtils.logOutAlert();
    }
}
