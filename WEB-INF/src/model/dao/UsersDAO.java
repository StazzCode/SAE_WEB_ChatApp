package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.dto.User;

public class UsersDAO {

    private Connection con;

    public UsersDAO() throws SQLException{
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

    public User findById(int id) throws SQLException{
        String query = "SELECT * FROM utilisateur WHERE pk_user = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            User utilisateur = new User();
            utilisateur.setId(rs.getInt("pk_user"));
            utilisateur.setUsername(rs.getString("username"));
            utilisateur.setPassword(rs.getString("password"));
            utilisateur.setCreatedAt(rs.getTimestamp("created_at"));
            return utilisateur;
        }
        return null;
    }

    public void create(User joueur) throws SQLException{
        String query = "INSERT INTO utilisateur (username, password, created_at) VALUES (?, ?, ?)";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setString(1, joueur.getUsername());
        ps.setString(2, joueur.getPassword());
        ps.setTimestamp(3, joueur.getCreatedAt());
        ps.executeUpdate();
    }

    public List<User> findAll() throws SQLException{
        List<User> utilisateurs = new ArrayList<User>();
        String query = "SELECT * FROM utilisateur";
        PreparedStatement ps = this.con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            User utilisateur = new User();
            utilisateur.setId(rs.getInt("pk_user"));
            utilisateur.setUsername(rs.getString("username"));
            utilisateur.setPassword(rs.getString("password"));
            utilisateur.setCreatedAt(rs.getTimestamp("created_at"));
            utilisateurs.add(utilisateur);
        }
        return utilisateurs;
    }

    public void delete(int id) throws SQLException{
        String query = "DELETE FROM utilisateur WHERE pk_user = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}