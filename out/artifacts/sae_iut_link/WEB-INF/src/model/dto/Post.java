package model.dto;

import java.sql.Timestamp;

public class Post {

    private int id;
    private User author;
    private int threadId;
    private String content;
    private Timestamp createdAt;

    public Post(int id, User author, int threadId, String content, Timestamp createdAt) {
        this.id = id;
        this.author = author;
        this.threadId = threadId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Post(User author, int threadId, String content) {
        this.author = author;
        this.threadId = threadId;
        this.content = content;
    }

    public Post() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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