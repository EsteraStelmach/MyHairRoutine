package dataBase.domain;

import javax.persistence.*;

@Entity
@Table(name = "oils")
public class Oils {


    @Id
    @GeneratedValue
    @Column(name = "idoil")
    private int id;
    private String name;
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
