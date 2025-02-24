package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ds.DS;
import model.dto.User;

public class UsersDAO implements DAO<User>{

    public User findById(int id) throws SQLException{
        User utilisateur = new User();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT id,username,password,created_at FROM users WHERE id = ?";
            String threadsQuery = "SELECT id,title,owner_id,created_at FROM threads WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                PreparedStatement threadsPS = con.prepareStatement(threadsQuery);
                ps.setInt(1, id);
                ResultSet threadsRS = ps.executeQuery();

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

    public List<User> findAll() throws SQLException{
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

    public void create(User joueur) throws SQLException{
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

    public void delete(int id) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void save(User user) throws SQLException {
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
}