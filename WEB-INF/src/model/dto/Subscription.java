package model.dto;

import java.sql.Time;

public class Subscription {
    
    private int id;
    private int userId;
    private int threadId;
    private Time createdAt;

    public Subscription(int id, int userId, int threadId) {
        this.id = id;
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

    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }
}
