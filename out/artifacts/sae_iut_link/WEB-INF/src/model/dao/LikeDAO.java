package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ds.DS;
import model.dto.Like;

public class LikeDAO implements DAO<Like>{

    public Like findById(int id) throws SQLException{
        Like like = new Like();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM likes WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                like.setId(rs.getInt("id"));
                like.setUserId(rs.getInt("user_id"));
                like.setCommentId(rs.getInt("post_id"));
                like.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return like;
    }

    public List<Like> findAll()throws SQLException{
        List<Like> likes = new ArrayList<Like>();
        try (Connection con = DS.instance.getConnection()){
            String query = "SELECT * FROM likes";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                likes.add(findById(rs.getInt("id")));
            }
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return likes;
    }

    public void create(Like joueur) throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "INSERT INTO likes (user_id, post_id, created_at) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, joueur.getUserId());
            ps.setInt(2, joueur.getCommentId());
            ps.setTimestamp(3, joueur.getCreatedAt());
            ps.executeUpdate();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id)throws SQLException{
        try (Connection con = DS.instance.getConnection()){
            String query = "DELETE FROM likes WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public void save(Like like) throws SQLException {
        try (Connection con = DS.instance.getConnection()){
            String query = "UPDATE likes SET user_id = ?, post_id = ?, created_at = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, like.getUserId());
            ps.setInt(2, like.getCommentId());
            ps.setTimestamp(3, like.getCreatedAt());
            ps.setInt(4, like.getId());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}