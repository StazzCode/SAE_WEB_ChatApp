package model.dto;

import java.sql.Timestamp;

public class Like {
    
    private int id;
    private int userId;
    private int commentId;
    private Timestamp createdAt;

    public Like(int id, int userId, int commentId, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.commentId = commentId;
        this.createdAt = createdAt;
    }

    public Like() {}

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

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}