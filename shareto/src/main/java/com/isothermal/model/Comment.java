package com.isothermal.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "content")
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "outlook_id")
    private Outlook outlook;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;
    @Transient
    private String updated_at_formatted;
    
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Outlook getOutlook() {
        return outlook;
    }
    public void setOutlook(Outlook outlook) {
        this.outlook = outlook;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    public String getUpdated_at_formatted() {
        return updated_at_formatted;
    }
    public void setUpdated_at_formatted(String updated_at_formatted) {
        this.updated_at_formatted = updated_at_formatted;
    }
    public Comment(String content, User user, Outlook outlook, Timestamp created_at, Timestamp updated_at) {
        this.content = content;
        this.user = user;
        this.outlook = outlook;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Comment() {
    }
    @Override
    public String toString() {
        return "Comment [id=" + id + ", content=" + content + ", user=" + user + ", outlook=" + outlook
                + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }

    

    

    

}
