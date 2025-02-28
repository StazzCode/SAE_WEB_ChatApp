package model.dto;

import java.sql.Timestamp;

public class Post {
    
    private int id;
    private int authorId;
    private int threadId;
    private String content;
    private Timestamp createdAt;

    public Post(int id, int authorId, int threadId, String content, Timestamp createdAt) {
        this.id = id;
        this.authorId = authorId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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