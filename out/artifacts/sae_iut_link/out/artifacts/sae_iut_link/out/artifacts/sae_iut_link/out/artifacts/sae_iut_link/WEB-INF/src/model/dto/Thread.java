package model.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Thread {
    
    private int id;
    private String title;
    private int userId;
    private String ownerUsername;
    private ArrayList<Post> posts;
    private Timestamp createdAt;

    public Thread(int id, String title, int userId, String ownerUsername, ArrayList<Post> posts, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.ownerUsername = ownerUsername;
        this.posts = posts;
        this.createdAt = createdAt;
    }

    public Thread() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getOwnerUsername() {return ownerUsername;}

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Post> getPosts() {return posts;}

    public void setPosts(ArrayList<Post> posts) {this.posts = posts;}
}