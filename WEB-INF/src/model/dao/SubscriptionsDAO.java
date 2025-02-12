package model.dao;
import java.sql.*;
import java.util.List;

import model.dto.Subscription;

public class SubscriptionsDAO {

    private Connection con;

    public SubscriptionsDAO() throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://psqlserv:5432/but2";
            String nom = "antoinedomisseetu";
            String mdp = "moi";
            this.con = DriverManager.getConnection(url,nom,mdp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Subscription findById(int id) throws SQLException{
        return null;
    }

    public void create(Subscription joueur) throws SQLException{

    }

    public List<Subscription> findAll(){
        return null;
    }

    public void delete(int id){

    }
}