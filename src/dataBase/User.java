package dataBase;

public class User {

    private int idUser;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String hairTwistType;
    private char hairPorosity;

    public User(int idUser, String login, String password) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHairTwistType() {
        return hairTwistType;
    }

    public void setHairTwistType(String hairTwistType) {
        this.hairTwistType = hairTwistType;
    }

    public char getHairPorosity() {
        return hairPorosity;
    }

    public void setHairPorosity(char hairPorosity) {
        this.hairPorosity = hairPorosity;
    }
}
