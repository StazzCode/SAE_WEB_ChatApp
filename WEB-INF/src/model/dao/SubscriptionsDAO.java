package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ds.DS;
import model.dto.Subscription;

public class SubscriptionsDAO implements DAO<Subscription>{

    public Subscription findById(int id) throws SQLException{
        Subscription subscription = new Subscription();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM subscriptions WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                subscription.setId(rs.getInt("id"));
                subscription.setUserId(rs.getInt("user_id"));
                subscription.setThreadId(rs.getInt("thread_id"));
                subscription.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return subscription;
    }

    public List<Subscription> findAll() throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            List<Subscription> subscriptions = new ArrayList<Subscription>();
            String query = "SELECT * FROM subscriptions";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                subscriptions.add(findById(rs.getInt("id")));
            }
            return subscriptions;
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void create(Subscription joueur) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "INSERT INTO subscriptions (user_id, thread_id, created_at) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, joueur.getUserId());
            ps.setInt(2, joueur.getThreadId());
            ps.setTimestamp(3, joueur.getCreatedAt());
            ps.executeUpdate();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "DELETE FROM subscriptions WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void save(Subscription subscription) throws SQLException {
        try (Connection con = DS.instance.getConnection()){
            String query = "UPDATE subscriptions SET user_id = ?, thread_id = ?, created_at = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, subscription.getUserId());
            ps.setInt(2, subscription.getThreadId());
            ps.setTimestamp(3, subscription.getCreatedAt());
            ps.setInt(4, subscription.getId());
            ps.executeUpdate();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}