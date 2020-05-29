package dataBase.domain;

import org.hibernate.cfg.annotations.reflection.XMLContext;

import javax.persistence.*;

@Entity
public class Conditioners {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private String type;
    private String coWash;
    private String leaveOn;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoWash() {
        return coWash;
    }

    public void setCoWash(String coWash) {
        this.coWash = coWash;
    }

    public String getLeaveOn() {
        return leaveOn;
    }

    public void setLeaveOn(String leaveOn) {
        this.leaveOn = leaveOn;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}