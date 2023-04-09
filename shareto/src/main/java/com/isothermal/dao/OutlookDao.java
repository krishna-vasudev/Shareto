package com.isothermal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.isothermal.model.Outlook;

@Component
public class OutlookDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void createOrUpdateOutlook(Outlook outlook){
        this.hibernateTemplate.saveOrUpdate(outlook);
    }

    public Outlook getOutlook(int id){
        return this.hibernateTemplate.get(Outlook.class, id);
    }

    public List<Outlook> getOutlooks(){
        return this.hibernateTemplate.loadAll(Outlook.class);
    }

    @Transactional
    public void deleteOutlook(int id){
        Outlook outlook=this.hibernateTemplate.get(Outlook.class,id);
        this.hibernateTemplate.delete(outlook);
    }
    
}
