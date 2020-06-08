package dataBase.domain;

import dataBase.UserUtils;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private  int id;
    @OneToMany
    @JoinColumn
    private List<WashRoutine> washRoutineList;
    private String login;
    private String password;
    @Column(name = "firstname")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "hairtwisttype")
    private String hairTwistType;
    @Column(name = "hairporosity")
    private String hairPorosity;


    public User()  {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHairTwistType() {
        return hairTwistType;
    }

    public String getHairPorosity() {
        return hairPorosity;
    }

    public void setFirstName(String firstName)  {
        this.name=firstName;
    }

    public void setLastName(String lastName)  {
     this.lastName=lastName;
    }

    public void setHairTwistType(String hairTwistType)  {
        if(hairTwistType == null){
            this.hairTwistType = " ";
        }else {
            this.hairTwistType = hairTwistType;
        }
    }

    public void setHairPorosity(String hairPorosity)  {
        String porosityInDataBAse = UserUtils.convertHairPorosityString(hairPorosity);
        this.hairPorosity = porosityInDataBAse;
    }

    public List<WashRoutine> getWashRoutineList() {
        return washRoutineList;
    }

    public void setWashRoutineList(List<WashRoutine> washRoutineList) {
        this.washRoutineList = washRoutineList;
    }
}
