package model.ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DS {
    public static DS instance = new DS();
    private DS() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }
    public Connection getConnection() throws SQLException{
        /*
        String url = "jdbc:postgresql://psqlserv/but2";
        String nom = "kellianmireyetu";
        String mdp = "moi";
         */

        String url = "jdbc:postgresql://psqlserv/but2";
        String nom = "antoinedomisseetu";
        String mdp = "moi";

        return DriverManager.getConnection(url,nom,mdp);
    }
}
