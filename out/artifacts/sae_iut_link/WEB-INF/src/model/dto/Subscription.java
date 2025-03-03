package model.dto;

import java.sql.Timestamp;
public class Subscription {
    
    private int id;
    private int userId;
    private int threadId;
    private Timestamp createdAt;

    public Subscription(int userId, int threadId) {
        this.userId = userId;
        this.threadId = threadId;
    }

    public Subscription() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
