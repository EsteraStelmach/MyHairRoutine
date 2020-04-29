package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends DataBaseConnection{


    private ArrayList<String> usersPassword = new ArrayList<>();
    private ArrayList<String> usersLogin = new ArrayList<>();

    public User(){}

    public void insertUser(String login,String password) throws SQLException {

        System.out.println("Adding....");
        String query= "INSERT INTO users (iduser,login,password) VALUES (null,?,?)";
        preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        preparedStatement.execute();
        preparedStatement.close();

    }


    public boolean isLoginAndPasswordCorrect(String userLoginInput, String userPasswordInput) {
        userLoginAndPasswordInDataBase();
        boolean isLoginCorrect=false;
        boolean isPasswordCorrect=false;
        for (String findLogin:usersLogin) {
            if(findLogin.equals(userLoginInput)){
                isLoginCorrect=true;
            }
        }
        for (String findPassword:usersPassword) {
            if(findPassword.equals(userPasswordInput)){
                isPasswordCorrect=true;
            }

        }
        return isLoginCorrect && isPasswordCorrect;
    }

   private void userLoginAndPasswordInDataBase(){
        ArrayList<String> results = new ArrayList<>();
        try {
            String query = "SELECT login,password FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String userLogin = resultSet.getString("login");
                String userPassword = resultSet.getString("password");
                usersLogin.add(userLogin);
                usersPassword.add(userPassword);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }





}
