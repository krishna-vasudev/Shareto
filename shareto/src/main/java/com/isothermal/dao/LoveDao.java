package com.isothermal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.isothermal.model.Love;
import com.isothermal.model.Outlook;
import com.isothermal.model.User;

@Component
public class LoveDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Transactional
    public void createOrUpdateLove(Love love){
        this.hibernateTemplate.saveOrUpdate(love);
    }

    public Love getLove(int id){
        return this.hibernateTemplate.get(Love.class, id);
    }

    public List<Love> getLoves(){
        return this.hibernateTemplate.loadAll(Love.class);
    }

    @Transactional
    public void deleteLove(int id){
        Love love=this.hibernateTemplate.get(Love.class, id);
        this.hibernateTemplate.delete(love);
    }

    public Love getLoveByOutlookUser(Outlook outlook,User user){
        DetachedCriteria criteria=DetachedCriteria.forClass(Love.class);
        criteria.add(Restrictions.eq("user", user));
        criteria.add(Restrictions.eq("outlook", outlook));

        List<Love>loves=(List<Love>) this.hibernateTemplate.findByCriteria(criteria);

        if(loves.size()==0){
            return null;
        }

        return loves.get(0);
    }

    
}
