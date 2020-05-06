package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserUtils {

    private static String login;
    private static int id;
    private static String password;
    private static String hairPorosity;
    private static String hairTwistType;
    private static String name;
    private static String lastName;

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundles.messages");

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        UserUtils.login = login;
    }


    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserUtils.name = name;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        UserUtils.lastName = lastName;
    }

    public static void setId(int id) {
        UserUtils.id = id;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserUtils.password = password;
    }

    public static String getHairPorosity() {
       String porosity=null;
       if(hairPorosity.equals("L")){
           porosity= resourceBundle.getString("secondRegisterWindow.choiceBox.label.low");
       }else if(hairPorosity.equals("M")){
           porosity= resourceBundle.getString("secondRegisterWindow.choiceBox.label.medium");
       }else if(hairPorosity.equals("H")){
           porosity= resourceBundle.getString("secondRegisterWindow.choiceBox.label.high");
       }
       return porosity;
    }

    public static void setHairPorosity(String hairPorosity) {
        UserUtils.hairPorosity = hairPorosity;
    }

    public static String getHairTwistType() {
        return hairTwistType;
    }

    public static void setHairTwistType(String hairTwistType) {
        UserUtils.hairTwistType = hairTwistType;
    }

    public static void selectUserInformation(String login) throws SQLException {
        String query = "SELECT iduser,firstname,lastname,hairtwisttype,hairporosity FROM users WHERE login='"+login+"'";
        ResultSet resultSet =  (DataBaseConnection.makePreparedStatement(query)).executeQuery();
        while (resultSet.next()){
            setId(resultSet.getInt("iduser"));
            setName(resultSet.getString("firstname"));
            setLastName(resultSet.getString("lastname"));
            setHairPorosity(resultSet.getString("hairporosity"));
            setHairTwistType(resultSet.getString("hairtwisttype"));
        }
    }
}
