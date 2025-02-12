package model.dto;

import java.sql.Timestamp;

public class Comment {
    
    private int id;
    private String content;
    private int userId;
    private int threadId;
    private Timestamp createdAt;

    public Comment(int id, String content, int userId, int threadId, Timestamp createdAt) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.threadId = threadId;
        this.createdAt = createdAt;
    }

    public Comment() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}