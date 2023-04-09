package com.isothermal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.isothermal.model.Comment;

@Component
public class CommentDao {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Transactional
    public void createOrUpdateComment(Comment comment){
        this.hibernateTemplate.saveOrUpdate(comment);
    }

    public Comment getComment(int id){
         return this.hibernateTemplate.get(Comment.class, id);
    }
    
    public List<Comment> getComments(){
        return this.hibernateTemplate.loadAll(Comment.class);
    }
    
    @Transactional
    public void deleteComment(int id){
        Comment comment=this.hibernateTemplate.get(Comment.class,id);
        this.hibernateTemplate.delete(comment);
    }


    
}
