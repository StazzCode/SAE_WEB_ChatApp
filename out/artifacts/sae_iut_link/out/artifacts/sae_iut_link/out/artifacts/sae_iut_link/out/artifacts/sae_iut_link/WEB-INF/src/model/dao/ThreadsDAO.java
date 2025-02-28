package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ds.DS;
import model.dto.Post;
import model.dto.Thread;

public class ThreadsDAO implements DAO<Thread>{

    public Thread findById(int id) throws SQLException{
        Thread thread = new Thread();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM threads WHERE id = ?";
            String postsQuery = "SELECT id,author_id,thread_id,content,created_at FROM posts WHERE thread_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                PreparedStatement postsPS = con.prepareStatement(postsQuery);
                postsPS.setInt(1, id);
                ResultSet postsRS = postsPS.executeQuery();
                ArrayList<Post> posts = new ArrayList<>();
                while (postsRS.next()){
                    Post post = new Post();
                    post.setAuthor(new UsersDAO().findById(postsRS.getInt("author_id")));
                    post.setThreadId(postsRS.getInt("thread_id"));
                    post.setContent(postsRS.getString("content"));
                    posts.add(post);
                }
                thread.setId(rs.getInt("id"));
                thread.setTitle(rs.getString("title"));
                thread.setUserId(rs.getInt("owner_id"));
                thread.setPosts(posts);
                thread.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return thread;
    }

    public List<Thread> findAll() {
        List<Thread> threads = null;
        try (Connection con = DS.instance.getConnection()) {
            threads = new ArrayList<Thread>();
            String query = "SELECT * FROM threads";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                threads.add(findById(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return threads;
    }

    public void create(Thread thread) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "INSERT INTO threads (title, owner_id, created_at) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, thread.getTitle());
            ps.setInt(2, thread.getUserId());
            ps.setTimestamp(3, thread.getCreatedAt());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "DELETE FROM threads WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    public void save(Thread e) throws SQLException {
        try (Connection con = DS.instance.getConnection()){
            String query = "UPDATE threads SET title = ?, owner_id = ?, created_at = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, e.getTitle());
            ps.setInt(2, e.getUserId());
            ps.setTimestamp(3, e.getCreatedAt());
            ps.setInt(4, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}