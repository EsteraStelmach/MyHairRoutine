package utils;

import Controllers.AddNewWashController;
import ExtendedClassess.WrappingTextFieldTableCell;
import dataBase.UserUtils;
import dataBase.WashRoutineUtils;
import dataBase.domain.WashRoutine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import javax.persistence.EntityManager;
import java.util.ResourceBundle;

public class MainApplicationMyWashRoutineUtils {


    private static ObservableList<WashRoutine> washRoutinesObservableList = FXCollections.observableArrayList();
    private static String helpValue;


    public static ObservableList<WashRoutine> getWashRoutinesObservableList() {
        washRoutinesObservableList.clear();
        washRoutinesObservableList.addAll(UserUtils.getWashRoutineList());
        return washRoutinesObservableList;
    }


     public static void makeColumnMyRoutineTable(TableColumn<WashRoutine,String> tableColumn) {
        tableColumn.setCellFactory(e -> {
            TableCell<WashRoutine, String> cell = new TextFieldTableCell<WashRoutine,String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        Text text = new Text(item);
                        this.setGraphic(text);
                        text.wrappingWidthProperty().bind(tableColumn.widthProperty().subtract(0));
                    }
                }
            };

            return cell;

         });
    }




    public static void addWashRoutine(EntityManager entityManager){
        Scene addWashRoutineScene = new Scene(fxmlUtils.fxmlLoader("/fxml/AddWashRoutine.fxml"));
        Stage stage = new Stage();
        stage.setScene(addWashRoutineScene);
        stage.showAndWait();
        String washingDescription = AddNewWashController.getWashingDescription();
        String washType = AddNewWashController.getWashType();
        UserUtils.addNewWash(washType,washingDescription,entityManager);

    }














}
