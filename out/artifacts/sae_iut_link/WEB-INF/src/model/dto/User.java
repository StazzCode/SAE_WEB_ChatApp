package model.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

public class User {
    
    private int id;
    private String username;
    private String password;
    private ArrayList<Thread> threads;
    private Timestamp createdAt;

    public User(int id, String username, String password, ArrayList<Thread> threads, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.threads = threads;
        this.createdAt = createdAt;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<Thread> threads) {
        this.threads = threads;
    }
}