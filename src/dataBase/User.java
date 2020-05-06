package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class User {



    private ArrayList<String> usersPassword = new ArrayList<>();
    private ArrayList<String> usersLogin = new ArrayList<>();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundles.messages");

    public User()  {
    }


    public void insertUser(String login, String password) throws SQLException {

        String query = "INSERT INTO users (iduser,login,password) VALUES (NULL,?,?)";
        PreparedStatement preparedStatement = (DataBaseConnection.makePreparedStatement(query));

        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
        preparedStatement.close();

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
        upDatePrepareStatement("firstname",firstName);
    }

    public void setLastName(String lastName) throws SQLException {
      upDatePrepareStatement("lastname",lastName);
    }

    public void setHairTwistType(String hairTwistType) throws SQLException {
        upDatePrepareStatement("hairtwisttype",hairTwistType);
    }

    public void setHairPorosity(String hairPorosity) throws SQLException {
        upDatePrepareStatement("hairporosity",convertHairPorosityString(hairPorosity));
    }


    private void upDatePrepareStatement(String column, String value) throws SQLException {
        String query = "UPDATE users SET "+column+"=? WHERE login=?";
        PreparedStatement preparedStatement =  (DataBaseConnection.makePreparedStatement(query));
        preparedStatement.setString(1,value);
        preparedStatement.setString(2,UserUtils.getLogin());
        preparedStatement.execute();
        preparedStatement.close();
    }

    private String convertHairPorosityString(String hairPorosity){
        String porosity=null;
        if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.low"))){
            porosity="L";
        }else if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.medium"))){
            porosity="M";
        }else if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.high"))){
            porosity="H";
        }
        return porosity;
    }



































}
