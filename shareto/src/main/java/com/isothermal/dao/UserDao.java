package com.isothermal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.isothermal.model.User;

@Component
public class UserDao {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Transactional
    public void createOrUpdateUser(User user){
        this.hibernateTemplate.saveOrUpdate(user);
    }
    
    public User getUser(int id){
        return this.hibernateTemplate.get(User.class, id);
    }

    public List<User> getUsers(){
        return this.hibernateTemplate.loadAll(User.class);
    }

    @Transactional
    public void deleteUser(int id){
        User user=this.hibernateTemplate.get(User.class, id);
        this.hibernateTemplate.delete(user);
    }

    public User getUserFromEmailAndPassword(String email,String password){
        DetachedCriteria criteria=DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("email",email));
        criteria.add(Restrictions.eq("password", password));
        criteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
        List<User> users=(List<User>) this.hibernateTemplate.findByCriteria(criteria);
        if(users.size()==0){
            return null;
        }
        User user=users.get(0);
        return user;
    }

}
