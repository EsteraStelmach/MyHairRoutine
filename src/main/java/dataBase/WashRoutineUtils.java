package dataBase;

import Controllers.MainApplicationWindowController;
import dataBase.domain.WashRoutine;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WashRoutineUtils {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundles.messages");
    private static MainApplicationWindowController mainApplicationWindowController = new MainApplicationWindowController();

    public static List<WashRoutine> createDefaultWashRoutineForBeginning(EntityManager entityManager){
        List<WashRoutine> defaultWashRoutines = new ArrayList<>();
        WashRoutine wash1 = new WashRoutine();
        WashRoutine wash2 = new WashRoutine();
        WashRoutine wash3 = new WashRoutine();
        WashRoutine wash4 = new WashRoutine();

        wash1.setWashType(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.wash1"));
        wash2.setWashType(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.wash2"));
        wash3.setWashType(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.wash3"));
        wash4.setWashType(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.wash4"));

        wash1.setWashingDescription(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.washingDescription1"));
        wash2.setWashingDescription(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.washingDescription2"));
        wash3.setWashingDescription(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.washingDescription3"));
        wash4.setWashingDescription(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.washingDescription4"));

        defaultWashRoutines.add(wash1);
        defaultWashRoutines.add(wash2);
        defaultWashRoutines.add(wash3);
        defaultWashRoutines.add(wash4);


        for(WashRoutine wash : defaultWashRoutines) {
            wash.setNumberWash(resourceBundle.getString("mainApplicationUtils.washRoutineTableView.wash") + (defaultWashRoutines.indexOf(wash) + 1));
        }
        return defaultWashRoutines;

    }

    public static WashRoutine createNewWash(String washNumber,String washType,String washingDescription){
        WashRoutine newWashRoutine = new WashRoutine();
        newWashRoutine.setNumberWash(washNumber);
        newWashRoutine.setWashType(washType);
        newWashRoutine.setWashingDescription(washingDescription);
        return newWashRoutine;
    }

    public static void upDateWashRoutine(TableCell<WashRoutine,String> cell,String newValue, EntityManager entityManager){
        TableColumn tableColumn = cell.getTableColumn();
        WashRoutine washRoutine = cell.getTableView().getSelectionModel().getSelectedItem();
        if(tableColumn.equals(mainApplicationWindowController.getWashingDescriptionColumnMyRoutineTable())){
            washRoutine.setWashingDescription(newValue);
        }else if(tableColumn.equals(mainApplicationWindowController.getWashTypeColumnMyRoutineTable())){
            washRoutine.setWashType(newValue);
        }else if(tableColumn.equals(mainApplicationWindowController.getWashNumberColumnMyRoutineTable())){
            washRoutine.setNumberWash(newValue);
        }
        entityManager.refresh(washRoutine);

    }



























}
