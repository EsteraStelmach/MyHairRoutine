package dataBase;

import utils.DialogsUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {

    private int idUser;
    private String userLogin;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String hairTwistType;
    private String hairPorosity;


    private ArrayList<String> usersPassword = new ArrayList<>();
    private ArrayList<String> usersLogin = new ArrayList<>();


    public User()  {
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void insertUser(String login, String password) throws SQLException {

        String query = "INSERT INTO users (iduser,login,password) VALUES (NULL,?,?)";

        PreparedStatement preparedStatement = (DataBaseConnection.makePreparedStatement(query));

        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
        preparedStatement.close();
        this.userLogin=login;
        this.userPassword=password;
    }


    public boolean isLoginAndPasswordCorrect(String userLoginInput, String userPasswordInput) throws SQLException {
        return isLoginCorrect(userLoginInput) && isPasswordCorrect(userPasswordInput);
    }

    private boolean isLoginCorrect(String userLoginInput) throws SQLException {
        userLoginAndPasswordInDataBase();
        boolean isLoginCorrect=false;
        for (String findLogin:usersLogin) {
            if(findLogin.equals(userLoginInput)){
                isLoginCorrect=true;
                userLogin = userLoginInput;
            }
        }
        return isLoginCorrect;
    }

    private boolean isPasswordCorrect(String userPasswordInput) throws SQLException {
        userLoginAndPasswordInDataBase();
        boolean isPasswordCorrect=false;
        for (String findPassword:usersPassword) {
            if(findPassword.equals(userPasswordInput)){
                isPasswordCorrect=true;
                userPassword= userPasswordInput;
            }
        }
        return isPasswordCorrect;
    }

   private void userLoginAndPasswordInDataBase() throws SQLException {
       String query = "SELECT iduser,login,password FROM users";

       ResultSet resultSet =  (DataBaseConnection.makePreparedStatement(query)).executeQuery();
       while (resultSet.next()){
           String userLogin = resultSet.getString("login");
           String userPassword = resultSet.getString("password");
           usersLogin.add(userLogin);
           usersPassword.add(userPassword);
       }
    }

    public boolean isThisUserLoginNotExists(String login) throws SQLException {
        userLoginAndPasswordInDataBase();
        boolean notExists = false;
        for(String helpLogin:usersLogin){
            if(helpLogin.equals(login)){
                notExists=true;
            }
        }
        return notExists;
    }

    public void setFirstName(String firstName) throws SQLException {
        this.firstName=firstName;
        upDatePrepareStatement("firstname",firstName);
    }

    public void setLastName(String lastName) throws SQLException {
        this.lastName=lastName;
      upDatePrepareStatement("lastname",lastName);
    }

    public void setHairTwistType(String hairTwistType) throws SQLException {
        this.hairTwistType=hairTwistType;
        upDatePrepareStatement("hairtwisttype",hairTwistType);
    }

    public void setHairPorosity(String hairPorosity) throws SQLException {
        this.hairPorosity=hairPorosity;
        upDatePrepareStatement("hairporosity",hairPorosity);
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUserLogin() {
        return userLogin;
    }

    private void upDatePrepareStatement(String column, String value) throws SQLException {
        String query = "UPDATE users SET "+column+"=? WHERE iduser=?";
        PreparedStatement preparedStatement =  (DataBaseConnection.makePreparedStatement(query));
        preparedStatement.setString(1,value);
        preparedStatement.setInt(2,this.idUser);
        preparedStatement.execute();
        preparedStatement.close();


    }

    public int selectIdUser(String login) throws SQLException {
       String query = "SELECT iduser FROM users WHERE login='"+login+"'";
       ResultSet resultSet =  (DataBaseConnection.makePreparedStatement(query)).executeQuery();
       while (resultSet.next()){
           this.idUser = resultSet.getInt("iduser");
           System.out.println(idUser);
       }
       return idUser;
    }

































}
