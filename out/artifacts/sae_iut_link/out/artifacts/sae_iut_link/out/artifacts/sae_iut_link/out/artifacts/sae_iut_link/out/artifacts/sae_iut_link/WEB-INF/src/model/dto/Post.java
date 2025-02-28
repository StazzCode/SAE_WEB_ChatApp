package model.dto;

public class Post {

    private User author;
    private int threadId;
    private String content;

    public Post(User author, int threadId, String content) {
        this.author = author;
        this.threadId = threadId;
        this.content = content;
    }

    public Post() {}

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
}