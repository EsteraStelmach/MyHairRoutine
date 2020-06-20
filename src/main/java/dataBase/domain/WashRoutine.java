package dataBase.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
public class WashRoutine {

    @Id
    @GeneratedValue
    @Column(name = "idwash",nullable = false)
    private int idWash;
    private String numberWash;
    private String washType;
    private String washingDescription;

    private static StringProperty washNumberProperty = new SimpleStringProperty();


    public int getIdWash() {
        return idWash;
    }

    public void setIdWash(int idWash) {
        this.idWash = idWash;
    }

    public String getNumberWash() {
        return numberWash;
    }

    public void setNumberWash(String numberWash) {
        this.numberWash = numberWash;
    }

    public String getWashType() {
        return washType;
    }

    public void setWashType(String washType) {
        this.washType = washType;
    }

    public String getWashingDescription() {
        return washingDescription;
    }

    public void setWashingDescription(String washingDescription) {
        this.washingDescription = washingDescription;
    }




























}
