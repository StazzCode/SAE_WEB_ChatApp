package model.dto;

import java.sql.Timestamp;

public class Post {
    
    private int id;
    private int senderId;
    private int threadId;
    private String content;
    private Timestamp createdAt;

    public Post(int id, int senderId, int threadId, String content, Timestamp createdAt) {
        this.id = id;
        this.senderId = senderId;
        this.threadId = threadId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Post() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }
}