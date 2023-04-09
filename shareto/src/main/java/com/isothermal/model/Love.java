package com.isothermal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="love")
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "outlook_id")
    private Outlook outlook;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public Love( User user, Outlook outlook) {
        this.user = user;
        this.outlook = outlook;
    }
    public Love() {
    }
    @Override
    public String toString() {
        return "Love [id=" + id + ", user=" + user + ", outlook=" + outlook + "]";
    }
    

    
    

    

    
}
