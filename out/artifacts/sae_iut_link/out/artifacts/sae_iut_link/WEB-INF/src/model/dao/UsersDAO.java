package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ds.DS;
import model.dto.Thread;
import model.dto.User;

public class UsersDAO implements DAO<User>{

    public User findById(int id){
        User utilisateur = new User();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT id,username,password,created_at FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setUsername(rs.getString("username"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setThreads(getThreads(id));
                utilisateur.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return utilisateur;
    }

    public User findByUsername(String username){
        User utilisateur = new User();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT id,username,password,created_at FROM users WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setUsername(rs.getString("username"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return utilisateur;
    }

    public ArrayList<User> findAllFromSubscription(int subscriptionId){
        ArrayList <User> utilisateurs = new ArrayList<User>();
        try (Connection con = DS.instance.getConnection()){
            String query = "Select u.id,username,password,u.created_at from users as u, subscriptions as s WHERE u.id = s.user_id AND s.thread_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                utilisateurs.add(user);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return utilisateurs;
    }

    public List<User> findAll(){
        List<User> utilisateurs = new ArrayList<User>();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                utilisateurs.add(findById(rs.getInt("id")));
            }
            return utilisateurs;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return utilisateurs;
    }

    public void create(User joueur) {
        try (Connection con = DS.instance.getConnection()){
            String query = "INSERT INTO users (username, password, created_at) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, joueur.getUsername());
            ps.setString(2, joueur.getPassword());
            ps.setTimestamp(3, joueur.getCreatedAt());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection con = DS.instance.getConnection()){
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void save(User user) {
        try (Connection con = DS.instance.getConnection()){
            String query = "UPDATE users SET username = ?, password = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean save(String username, String password) {
        try (Connection con = DS.instance.getConnection()){
            String query = "INSERT INTO users (username, password, created_at) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Thread> getThreads(int id){
        ArrayList<Thread> threads = new ArrayList<>();
        try (Connection con = DS.instance.getConnection()){
            //String query = "SELECT id,title,owner_id,created_at FROM threads WHERE owner_id = ?";
            String query = "SELECT t.id,t.title,t.owner_id,t.created_at FROM threads as t,subscriptions as s WHERE t.id = s.thread_id AND s.user_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            setThreads(threads, rs);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return threads;
    }

    private void setThreads(ArrayList<Thread> threads, ResultSet rs) throws SQLException {
        while(rs.next()){
            Thread thread = new Thread();
            thread.setId(rs.getInt("id"));
            thread.setTitle(rs.getString("title"));
            thread.setUserId(rs.getInt("owner_id"));
            thread.setCreatedAt(rs.getTimestamp("created_at"));
            threads.add(thread);
        }
    }
}