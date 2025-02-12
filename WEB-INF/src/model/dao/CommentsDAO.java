package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.dto.Comment;

public class CommentsDAO{ 

    private Connection con;

    public CommentsDAO() throws SQLException{
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

    public Comment findById(int id) throws SQLException{
        String query = "SELECT * FROM comment WHERE pk_comment = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Comment comment = new Comment();
            comment.setId(rs.getInt("pk_comment"));
            comment.setContent(rs.getString("content"));
            comment.setUserId(rs.getInt("fk_user"));
            comment.setThreadId(rs.getInt("fk_thread"));
            comment.setCreatedAt(rs.getTimestamp("created_at"));
            return comment;
        }
        return null;
    }

    public void create(Comment joueur) throws SQLException{
        String query = "INSERT INTO comment (content, fk_user, fk_thread, created_at) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setString(1, joueur.getContent());
        ps.setInt(2, joueur.getUserId());
        ps.setInt(3, joueur.getThreadId());
        ps.setTimestamp(4, joueur.getCreatedAt());
        ps.executeUpdate();
    }

    public List<Comment> findAll() throws SQLException{
        List<Comment> comments = new ArrayList<Comment>();
        String query = "SELECT * FROM comment";
        PreparedStatement ps = this.con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Comment comment = new Comment();
            comment.setId(rs.getInt("pk_comment"));
            comment.setContent(rs.getString("content"));
            comment.setUserId(rs.getInt("fk_user"));
            comment.setThreadId(rs.getInt("fk_thread"));
            comment.setCreatedAt(rs.getTimestamp("created_at"));
            comments.add(comment);
        }
        return comments;
    }

    public void delete(int id) throws SQLException{
        String query = "DELETE FROM comment WHERE pk_comment = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}