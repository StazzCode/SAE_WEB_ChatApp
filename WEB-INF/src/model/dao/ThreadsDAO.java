package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.dto.Thread;

public class ThreadsDAO {

    private Connection con;

    public ThreadsDAO() throws SQLException{
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

    public Thread findById(int id) throws SQLException{
        String query = "SELECT * FROM thread WHERE pk_thread = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Thread thread = new Thread();
            thread.setId(rs.getInt("pk_thread"));
            thread.setTitle(rs.getString("title"));
            thread.setContent(rs.getString("content"));
            thread.setUserId(rs.getInt("fk_user"));
            thread.setCreatedAt(rs.getTimestamp("created_at"));
            return thread;
        }
        return null;
    }

    public void create(Thread joueur) throws SQLException{
        String query = "INSERT INTO thread (title, content, fk_user, created_at) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setString(1, joueur.getTitle());
        ps.setString(2, joueur.getContent());
        ps.setInt(3, joueur.getUserId());
        ps.setTimestamp(4, joueur.getCreatedAt());
        ps.executeUpdate();
    }

    public List<Thread> findAll() throws SQLException{
        List<Thread> threads = new ArrayList<Thread>();
        String query = "SELECT * FROM thread";
        PreparedStatement ps = this.con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Thread thread = new Thread();
            thread.setId(rs.getInt("pk_thread"));
            thread.setTitle(rs.getString("title"));
            thread.setContent(rs.getString("content"));
            thread.setUserId(rs.getInt("fk_user"));
            thread.setCreatedAt(rs.getTimestamp("created_at"));
            threads.add(thread);
        }
        return threads;
    }

    public void delete(int id) throws SQLException{
        String query = "DELETE FROM thread WHERE pk_thread = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        
    }
}