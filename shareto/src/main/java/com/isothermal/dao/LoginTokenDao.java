package com.isothermal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.isothermal.model.LoginToken;

@Component
public class LoginTokenDao {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void createOrUpdateLoginToken(LoginToken loginToken){
        this.hibernateTemplate.saveOrUpdate(loginToken);
    }

    public LoginToken getLoginToken(int id){
        return this.hibernateTemplate.get(LoginToken.class, id);
    }

    public List<LoginToken> getLoginTokens(){
        return this.hibernateTemplate.loadAll(LoginToken.class);
    }

    @Transactional
    public void deleteLoginToken(int id){
        LoginToken loginToken=this.hibernateTemplate.get(LoginToken.class, id);
        this.hibernateTemplate.delete(loginToken);
    }

    public LoginToken getLoginTokenByToken(String token){
        DetachedCriteria criteria=DetachedCriteria.forClass(LoginToken.class);
        criteria.add(Restrictions.eq("token", token));
        List<LoginToken>loginTokens=(List<LoginToken>) this.hibernateTemplate.findByCriteria(criteria);
        if(loginTokens.size()==0){
            return null;
        }

        return loginTokens.get(0);
    }
}
