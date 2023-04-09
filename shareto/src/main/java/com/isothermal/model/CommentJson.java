package com.isothermal.model;

public class CommentJson {
    
    private int id;
    
    private String content;
    
    private String user_name;

    private int user_id;
    
    private String updated_at_formatted;

    public CommentJson(int id, String content, String user_name, String updated_at_formatted,int user_id) {
        this.id = id;
        this.content = content;
        this.user_name = user_name;
        this.updated_at_formatted = updated_at_formatted;
        this.user_id=user_id;
    }

    public CommentJson() {
    }

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUpdated_at_formatted() {
        return updated_at_formatted;
    }

    public void setUpdated_at_formatted(String updated_at_formatted) {
        this.updated_at_formatted = updated_at_formatted;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
}
