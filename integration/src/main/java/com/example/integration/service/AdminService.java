package com.example.integration.service;

import com.example.integration.models.Admin;
import lars.repo.Repo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
public class AdminService {
    @Autowired
    Repo<Admin> repo;
    public Admin logAdmin(Admin admin) throws IllegalAccessException {
        repo.init(Admin.class);
        Session session = repo.getFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" SELECT ad FROM Admin ad WHERE ad.password=:mdp AND ad.username=:username");
        query.setParameter("mdp", admin.getPassword());
        query.setParameter("username",admin.getUsername());
        Admin result = (Admin) query.uniqueResult();
        transaction.commit();
        session.close();
        repo.closeWithoutError();
        if(result ==null) {
            System.out.println("it s null");
        }
        return result;
    }
    public boolean register(Admin admin) throws IllegalAccessException{
        repo.init(Admin.class);
        Session session = repo.getFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" SELECT count(ad) from Admin ad");
        Long count1 = (Long) query.uniqueResult();
        session.save(admin);
        session.refresh(admin);
        query = session.createQuery(" SELECT count(ad) from Admin ad");
        Long count2 = (Long) query.uniqueResult();
        boolean rep = true;
        if(count2>count1){
            transaction.commit();
            rep= true;
        }
        else {
            transaction.rollback();
            rep= false;
        }
        session.close();
        repo.closeWithoutError();
       return rep;
    }

}
