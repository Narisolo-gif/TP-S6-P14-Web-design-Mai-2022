package com.example.integration.service;

import com.example.integration.models.Article;
import com.example.integration.models.Content;
import jakarta.persistence.Query;
import lars.repo.Repo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    Repo<Article> repo;

    public void saveArticle(Article article){
        Session session = repo.getFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(article);
        transaction.commit();
        session.close();
    }
    public List<Article> listerArticle(){
        repo.init(Article.class);
        List<Article> list = repo.getAll();
        repo.closeWithoutError();
        return list;
    }
    public void UpdateArticle(Article article){
        repo.init(Article.class);
        Session session = repo.getFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(article);
        transaction.commit();
        session.close();
    }
}
