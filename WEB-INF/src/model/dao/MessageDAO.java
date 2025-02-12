package model.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.dto.Message;

public class MessageDAO {

    private Connection con;

    public MessageDAO() throws SQLException{
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

    public Message findById(int id) throws SQLException{
        String query = "SELECT * FROM message WHERE pk_message = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Message message = new Message();
            message.setId(rs.getInt("pk_message"));
            message.setSenderId(rs.getInt("fk_sender"));
            message.setReceiverId(rs.getInt("fk_receiver"));
            message.setContent(rs.getString("content"));
            message.setCreatedAt(rs.getTimestamp("created_at"));
            return message;
        }
        return null;
    }

    public void create(Message joueur) throws SQLException{
        String query = "INSERT INTO message (fk_sender, fk_receiver, content, created_at) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, joueur.getSenderId());
        ps.setInt(2, joueur.getReceiverId());
        ps.setString(3, joueur.getContent());
        ps.setTimestamp(4, joueur.getCreatedAt());
        ps.executeUpdate();
    }

    public List<Message> findAll() throws SQLException{
        List<Message> messages = new ArrayList<Message>();
        String query = "SELECT * FROM message";
        PreparedStatement ps = this.con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Message message = new Message();
            message.setId(rs.getInt("pk_message"));
            message.setSenderId(rs.getInt("fk_sender"));
            message.setReceiverId(rs.getInt("fk_receiver"));
            message.setContent(rs.getString("content"));
            message.setCreatedAt(rs.getTimestamp("created_at"));
            messages.add(message);
        }
        return messages;
    }

    public void delete(int id) throws SQLException{
        String query = "DELETE FROM message WHERE pk_message = ?";
        PreparedStatement ps = this.con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}