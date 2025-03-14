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
                post.setAuthor(new UsersDAO().findById(rs.getInt("author_id")));
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

    public List<Post> findAll() {
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

    public void create(Post post) {
        try (Connection con = DS.instance.getConnection()){
            String query = "INSERT INTO Posts (content, author_id, thread_id) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, post.getContent());
            ps.setInt(2, post.getAuthor().getId());
            ps.setInt(3, post.getThreadId());
            ps.executeUpdate();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
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
            String query = "UPDATE Posts SET content = ?, author_id = ?, thread_id = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, post.getContent());
            ps.setInt(2, post.getAuthor().getId());
            ps.setInt(3, post.getThreadId());
            ps.setInt(4, post.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}