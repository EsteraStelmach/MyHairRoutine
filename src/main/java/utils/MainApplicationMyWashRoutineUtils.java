package utils;

import dataBase.UserUtils;
import dataBase.domain.WashRoutine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ResourceBundle;

public class MainApplicationMyWashRoutineUtils {


    private static ObservableList<WashRoutine> washRoutinesObservableList = FXCollections.observableArrayList(UserUtils.getWashRoutineList());


    public static ObservableList<WashRoutine> getWashRoutinesObservableList() {
        return washRoutinesObservableList;
    }

     public static void makeWashingDescriptionColumnMyRoutineTable(TableColumn<WashRoutine,String> tableColumn, String paramName) {
         tableColumn.setCellValueFactory(
                 new PropertyValueFactory<>(paramName));
         tableColumn.setCellFactory(e -> {
             TableCell<WashRoutine, String> cell = new TableCell<WashRoutine, String>() {
                 @Override
                 protected void updateItem(String item, boolean empty) {
                     super.updateItem(item, empty);
                     if (item != null) {
                         Text text = new Text(item);
                         this.setGraphic(text);
                         text.wrappingWidthProperty().bind(tableColumn.widthProperty().subtract(35));
                     }
                 }
             };
             return cell;
         });
    }
}
