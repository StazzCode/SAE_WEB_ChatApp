package model.dao;
import model.ds.DS;
import model.dto.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements DAO<Post>{

    public Post findById(int id) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM Posts WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setSenderId((rs.getInt("author_id")));
                post.setThreadId(rs.getInt("thread_id"));
                post.setContent(rs.getString("content"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                return post;
            }
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Post> findAll() throws SQLException{
        List<Post> Posts = new ArrayList<Post>();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM Posts";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Posts.add(findById(rs.getInt("id")));
            }
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return Posts;
    }

    public void create(Post joueur) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "INSERT INTO Posts (content, author_id, thread_id, created_at) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, joueur.getContent());
            ps.setInt(2, joueur.getSenderId());
            ps.setInt(3, joueur.getThreadId());
            ps.setTimestamp(4, joueur.getCreatedAt());
            ps.executeUpdate();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "DELETE FROM Posts WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void save(Post post) {
        try (Connection con = DS.instance.getConnection()){
            String query = "UPDATE Posts SET content = ?, author_id = ?, thread_id = ?, created_at = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, post.getContent());
            ps.setInt(2, post.getSenderId());
            ps.setInt(3, post.getThreadId());
            ps.setTimestamp(4, post.getCreatedAt());
            ps.setInt(5, post.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}