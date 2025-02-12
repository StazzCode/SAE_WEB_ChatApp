package model.dto;

import java.sql.Timestamp;

public class Thread {
    
    private int id;
    private String title;
    private String content;
    private int userId;
    private Timestamp createdAt;

    public Thread(int id, String title, String content, int userId, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}