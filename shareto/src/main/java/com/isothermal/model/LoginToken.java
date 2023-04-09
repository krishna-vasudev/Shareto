package com.isothermal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="login_token")
public class LoginToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NaturalId(mutable = true)
    @Column(name = "token",unique = true)
    private String token;
    @OneToOne(mappedBy = "loginToken")
    private User user;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LoginToken( String token, User user) {
        this.token = token;
        this.user = user;
    }
    public LoginToken() {
    }
    @Override
    public String toString() {
        return "LoginToken [id=" + id + ", token=" + token + ", user=" + user + "]";
    }

    
    

    

    
}
