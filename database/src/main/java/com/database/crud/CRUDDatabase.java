package com.database.crud;//package com.database.facades;

import com.database.model.Main;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUDDatabase {
    private static Connection connection;

    public static CRUDDatabase INSTANCE = new CRUDDatabase();
    private final static String HOST = "localhost/";
    private final static String USERID = "root";
    private final static String DATABASE = "fivem";
    private final static String PASSWORD = "rootserver";

    public CRUDDatabase() {
       connection = null;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + HOST + DATABASE, USERID, PASSWORD);
    }
    public void createMain(Main main) {
        try {
            connection = getConnection();
            PreparedStatement insert = connection.prepareStatement("INSERT INTO main(id,name,args) VALUES (?,?,?)");
            insert.setString(1, main.getId());
            insert.setString(2, main.getName());
            insert.setString(3, main.getArgs());
            System.out.println("createMain: Record save");
            insert.executeUpdate();

        }
        catch (Exception ex) {
            Logger.getLogger(CRUDDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Main getMainById(String value) {
        Main main = null;
        try {
            connection = getConnection();
            PreparedStatement insert = connection.prepareStatement("SELECT * FROM `main` WHERE id = ?");
            insert.setString(1, value);
            ResultSet resultSet = insert.executeQuery();
            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String args = resultSet.getString("args");
                main = new Main(id, name, args);
            }

            System.out.println("createMain: got main by id");
            return main;

        } catch (Exception ex) {
            Logger.getLogger(CRUDDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return main;
        }
    }
}
