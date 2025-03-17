package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ds.DS;
import model.dto.Post;
import model.dto.Thread;

public class ThreadsDAO implements DAO<Thread>{

    public Thread findById(int id){
        Thread thread = new Thread();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM threads WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                thread.setId(rs.getInt("id"));
                thread.setTitle(rs.getString("title"));
                thread.setUserId(rs.getInt("owner_id"));
                thread.setOwnerUsername(new UsersDAO().findById(rs.getInt("owner_id")).getUsername());
                thread.setPosts(getPosts(id));
                thread.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return thread;
    }

    public Thread findByTitle(String title){
        Thread thread = new Thread();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM threads WHERE title = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                thread.setId(rs.getInt("id"));
                thread.setTitle(rs.getString("title"));
                thread.setUserId(rs.getInt("owner_id"));
                thread.setOwnerUsername(new UsersDAO().findById(rs.getInt("owner_id")).getUsername());
                thread.setPosts(getPosts(rs.getInt("id")));
                thread.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return thread;
    }

    public ArrayList<Thread> findAll() {
        ArrayList<Thread> threads = null;
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

    public ArrayList<Thread> findWhereNotOwner(int id){
        ArrayList<Thread> threads = null;
        try (Connection con = DS.instance.getConnection()){
            threads = new ArrayList<Thread>();
            String query = "SELECT * FROM threads WHERE owner_id != ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                threads.add(findById(rs.getInt("id")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return threads;
    }

    public void create(Thread thread) {
        try (Connection con = DS.instance.getConnection()){
            String query = "INSERT INTO threads (title, owner_id) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, thread.getTitle());
            ps.setInt(2, thread.getUserId());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection con = DS.instance.getConnection()){
            String query = "DELETE FROM threads WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void save(Thread e) {
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

    public ArrayList<Post> getPosts(int id){
        ArrayList<Post> posts = null;
        try (Connection con = DS.instance.getConnection()){
            posts = new ArrayList<Post>();
            String query = "SELECT * FROM posts WHERE thread_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            setPosts(posts, rs);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return posts;
    }

    public void setPosts(ArrayList<Post> posts, ResultSet rs) throws SQLException {
        while (rs.next()){
            Post post = new Post();
            post.setId(rs.getInt("id"));
            post.setAuthor(new UsersDAO().findById(rs.getInt("author_id")));
            post.setThreadId(rs.getInt("thread_id"));
            post.setContent(rs.getString("content"));
            post.setCreatedAt(rs.getTimestamp("created_at"));
            posts.add(post);
        }

    }
}