package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.dto.Subscription;

public class SubscriptionsDAO implements DAO<Subscription>{

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
        String query = "SELECT * FROM subscriptions WHERE pk_subscription = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Subscription subscription = new Subscription();
            subscription.setId(rs.getInt("pk_subscription"));
            subscription.setUserId(rs.getInt("fk_user"));
            subscription.setThreadId(rs.getInt("fk_thread"));
            subscription.setCreatedAt(rs.getTimestamp("created_at"));
            return subscription;
        }
        return null;
    }

    public List<Subscription> findAll() throws SQLException{
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        String query = "SELECT * FROM subscriptions";
        PreparedStatement ps = this.con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Subscription subscription = new Subscription();
            subscription.setId(rs.getInt("pk_subscription"));
            subscription.setUserId(rs.getInt("fk_user"));
            subscription.setThreadId(rs.getInt("fk_thread"));
            subscription.setCreatedAt(rs.getTimestamp("created_at"));
            subscriptions.add(subscription);
        }
        return subscriptions;
    }

    public void create(Subscription joueur) throws SQLException{
        String query = "INSERT INTO subscriptions (fk_user, fk_thread, created_at) VALUES (?, ?, ?)";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, joueur.getUserId());
        ps.setInt(2, joueur.getThreadId());
        ps.setTimestamp(3, joueur.getCreatedAt());
        ps.executeUpdate();
    }

    public void delete(int id) throws SQLException{
        String query = "DELETE FROM subscriptions WHERE pk_subscription = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();        
    }

    public Subscription save(Subscription e) throws SQLException {
        String query = "UPDATE subscriptions SET fk_user = ?, fk_thread = ?, created_at = ? WHERE pk_subscription = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, e.getUserId());
        ps.setInt(2, e.getThreadId());
        ps.setTimestamp(3, e.getCreatedAt());
        ps.setInt(4, e.getId());
        ps.executeUpdate();
        return e;
    }
}