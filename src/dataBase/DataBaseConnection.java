package dataBase;

import Properties.DataBaseProperties;
import utils.DialogsUtils;

import java.sql.*;

public class DataBaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/hairroutine";
    private static DataBaseProperties dataBaseProperties = new DataBaseProperties();
    protected static Connection connection;
    private  ResultSet resultSet;

    private String query;
    protected PreparedStatement preparedStatement=null;

    //default
    //String user = "admin";
    //String password = "admin";

    //my localhost
    private static String user = dataBaseProperties.getUserProperty();
    private static String password = dataBaseProperties.getPasswordProperty();

    public DataBaseConnection() { }

    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            DialogsUtils.errorDialogConnectingToDataBase(e);
        }
        return connection;
    }
    public static PreparedStatement makePreparedStatement(String query){
        PreparedStatement preparedStatement=null;
        try {
           preparedStatement = connect().prepareStatement(query);
        } catch (Exception exception) {
            DialogsUtils.errorDialogConnectingToDataBase(exception);
        }
        return preparedStatement;

    }


}
