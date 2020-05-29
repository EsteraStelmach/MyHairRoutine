package dataBase.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shampoos")
public class Shampoos {


    @Id
    @GeneratedValue
    private int idShampoo;
    @Column(name = "stronglevel")
    private String strongLevel;
    private String name;
    private String notes;

    public int getIdShampoo() {
        return idShampoo;
    }

    public void setIdShampoo(int idShampoo) {
        this.idShampoo = idShampoo;
    }

    public String getStrongLevel() {
        return strongLevel;
    }

    public void setStrongLevel(String strongLevel) {
        this.strongLevel = strongLevel;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }




}
