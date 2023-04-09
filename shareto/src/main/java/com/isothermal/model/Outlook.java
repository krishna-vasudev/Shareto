package com.isothermal.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "outlook")
public class Outlook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;
    @Column(name = "body")
    private String body;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "outlook", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
        CascadeType.REFRESH },fetch = FetchType.EAGER)
    private Set<Love>loves;
    @OneToMany(mappedBy = "outlook", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
        CascadeType.REFRESH },fetch = FetchType.EAGER)
    private Set<Comment>comments;
    @Column(name = "views")
    private long views;
    @Transient
    private boolean lovedByLoggedInUser;
    @Transient
    private String updated_at_formatted;
    @Transient
    private List<Comment>comments_formatted;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Set<Love> getLoves() {
        return loves;
    }
    public void setLoves(Set<Love> loves) {
        this.loves = loves;
    }
    public Set<Comment> getComments() {
        return comments;
    }
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    public long getViews() {
        return views;
    }
    public void setViews(long views) {
        this.views = views;
    }

    
    public boolean isLovedByLoggedInUser() {
        return lovedByLoggedInUser;
    }
    public void setLovedByLoggedInUser(boolean lovedByLoggedInUser) {
        this.lovedByLoggedInUser = lovedByLoggedInUser;
    }
    
    public String getUpdated_at_formatted() {
        return updated_at_formatted;
    }
    public void setUpdated_at_formatted(String updated_at_formatted) {
        this.updated_at_formatted = updated_at_formatted;
    }
    
    public List<Comment> getComments_formatted() {
        return comments_formatted;
    }
    public void setComments_formatted(List<Comment> comments_formatted) {
        this.comments_formatted = comments_formatted;
    }
    

    public Outlook(String title,Timestamp created_at, Timestamp updated_at, String body, User user, long views) {
        this.title = title;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.body = body;
        this.user = user;
        this.views = views;
    }
    public Outlook() {
    }
    @Override
    public String toString() {
        return "Outlook [id=" + id + ", title=" + title + ", created_at=" + created_at + ", updated_at=" + updated_at
                + ", body=" + body + ", user=" + user + ", views=" + views + "]";
    }
    
    

    
    
    
    
}
