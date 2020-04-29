package dataBase;

import Properties.DataBaseProperties;

import java.sql.*;

public class DataBaseConnection {

    private String url = "jdbc:mysql://localhost:3306/hairroutine";
    private DataBaseProperties dataBaseProperties = new DataBaseProperties();
    protected Connection connection;
    private ResultSet resultSet;
    private Statement statement;
    private String query;
    protected PreparedStatement preparedStatement=null;

    //default
    //String user = "admin";
    //String password = "admin";

    //my localhost
    private String user = dataBaseProperties.getUserProperty();
    private String password = dataBaseProperties.getPasswordProperty();

    public DataBaseConnection() { }

    public void connect() {
        try {
            System.out.println("Connecting....");
            connection = DriverManager.getConnection(url, user, password);
            statement= connection.createStatement();
            if(!connection.isClosed()) System.out.println("dziąła");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


}
