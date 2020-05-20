package dataBase.domain;

import javax.persistence.*;

@Entity
@Table(name = "userroutine")
public class UserHairRoutine {

    @Id
    @GeneratedValue
    @Column(name = "idwash",nullable = false)
    private int idWash;
    private String wash1="M,E";
    private String wash2="O,H,L,E";
    private String wash3="P,L,E";
    private String wash4="E,LG,E";
    private String wash5="OH,E,L,E";
    private String wash6="M,E";
    private String wash7="PH,L,E";
    private String wash8="E,L,E";
    private String wash9="P,LG,E";
    private String wash10="OH,E,L,E";


    public int getIdWash() {
        return idWash;
    }

    public void setIdWash(int idWash) {
        this.idWash = idWash;
    }


    public String getWash1() {
        return wash1;
    }

    public void setWash1(String wash1) {
        this.wash1 = wash1;
    }

    public String getWash2() {
        return wash2;
    }

    public void setWash2(String wash2) {
        this.wash2 = wash2;
    }

    public String getWash3() {
        return wash3;
    }

    public void setWash3(String wash3) {
        this.wash3 = wash3;
    }

    public String getWash4() {
        return wash4;
    }

    public void setWash4(String wash4) {
        this.wash4 = wash4;
    }

    public String getWash5() {
        return wash5;
    }

    public void setWash5(String wash5) {
        this.wash5 = wash5;
    }

    public String getWash6() {
        return wash6;
    }

    public void setWash6(String wash6) {
        this.wash6 = wash6;
    }

    public String getWash7() {
        return wash7;
    }

    public void setWash7(String wash7) {
        this.wash7 = wash7;
    }

    public String getWash8() {
        return wash8;
    }

    public void setWash8(String wash8) {
        this.wash8 = wash8;
    }

    public String getWash9() {
        return wash9;
    }

    public void setWash9(String wash9) {
        this.wash9 = wash9;
    }

    public String getWash10() {
        return wash10;
    }

    public void setWash10(String wash10) {
        this.wash10 = wash10;
    }
}
