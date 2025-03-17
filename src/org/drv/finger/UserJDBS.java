package org.drv.finger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserJDBS {
    public static void main(String[] args){
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:score.db");
                Statement statement = connection.createStatement();){
            statement.executeUpdate("DROP TABLE IF EXISTS user");
            statement.setQueryTimeout(30);
            statement.executeUpdate("CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, bTime integer, bspm integer )");





        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
