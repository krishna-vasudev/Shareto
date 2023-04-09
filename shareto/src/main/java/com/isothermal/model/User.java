package com.isothermal.model;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @NaturalId(mutable = true)
    @Column(name = "email",unique = true)
    private String email;
    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
        CascadeType.REFRESH },fetch = FetchType.EAGER)
    private List<Outlook>outlooks;
    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
        CascadeType.REFRESH },fetch = FetchType.EAGER)
    private Set<Love>loves;
    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
        CascadeType.REFRESH },fetch = FetchType.EAGER)
    private Set<Comment>comments;
    @OneToOne
    @JoinColumn(name="loginToken_id")
    private LoginToken loginToken;
    @Column(name = "password")
    private String password;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Outlook> getOutlooks() {
        return outlooks;
    }
    public void setOutlooks(List<Outlook> outlooks) {
        this.outlooks = outlooks;
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
    public LoginToken getLoginToken() {
        return loginToken;
    }
    public void setLoginToken(LoginToken loginToken) {
        this.loginToken = loginToken;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    

    public User( String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password=password;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
    public User() {
    }
    
    
    public boolean is_anonymous(){
        if(this.id==0){
            return true;
        }

        return false;
    }
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(this==obj){
            return true;
        }

        if(!(obj instanceof User)){
            return false;
        }

        User that=(User) obj;
        EqualsBuilder eb=new EqualsBuilder();
        eb.append(this.email, that.email);

        return eb.isEquals();
        
    }
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        HashCodeBuilder hcb=new HashCodeBuilder();
        hcb.append(this.email);
        return hcb.toHashCode();
    }

    
    

    

    
    
}
