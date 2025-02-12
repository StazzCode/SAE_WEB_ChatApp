package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.dto.Like;

public class LikeDAO implements DAO<Like>{

    private Connection con;

    public LikeDAO() throws SQLException{
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

    public Like findById(int id) throws SQLException{
        String query = "SELECT * FROM likes WHERE pk_like = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Like like = new Like();
            like.setId(rs.getInt("pk_like"));
            like.setUserId(rs.getInt("fk_user"));
            like.setCommentId(rs.getInt("fk_comment"));
            like.setCreatedAt(rs.getTimestamp("created_at"));
            return like;
        }
        return null;
    }

    public List<Like> findAll()throws SQLException{
        List<Like> likes = new ArrayList<Like>();
        String query = "SELECT * FROM likes";
        PreparedStatement ps = this.con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Like like = new Like();
            like.setId(rs.getInt("pk_like"));
            like.setUserId(rs.getInt("fk_user"));
            like.setCommentId(rs.getInt("fk_comment"));
            like.setCreatedAt(rs.getTimestamp("created_at"));
            likes.add(like);
        }
        return likes;
    }

    public void create(Like joueur) throws SQLException{
        String query = "INSERT INTO likes (fk_user, fk_comment, created_at) VALUES (?, ?, ?)";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, joueur.getUserId());
        ps.setInt(2, joueur.getCommentId());
        ps.setTimestamp(3, joueur.getCreatedAt());
        ps.executeUpdate();
    }

    public void delete(int id)throws SQLException{
        String query = "DELETE FROM likes WHERE pk_like = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public Like save(Like e) throws SQLException {
        String query = "INSERT INTO likes (fk_user, fk_comment, created_at) VALUES (?, ?, ?)";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, e.getUserId());
        ps.setInt(2, e.getCommentId());
        ps.setTimestamp(3, e.getCreatedAt());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            e.setId(rs.getInt(1));
            return e;
        }
        return null;
    }
}