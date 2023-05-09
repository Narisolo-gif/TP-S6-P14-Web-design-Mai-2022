package com.example.integration.repo;

import com.example.integration.models.Content;
import lars.repo.Repo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ContentRepo extends Repo<Content> {
    public ContentRepo(SessionFactory factory){
        this.setFactory(factory);
        super.init(Content.class);
    }
    public List<Content> getWithKey(String key,int page,int size){
        String query="SELECT * FROM content WHERE UPPER(title) LIKE UPPER('%s') OR UPPER(content) LIKE UPPER('%s') OR UPPER(lieu) LIKE UPPER('%s') LIMIT "+ size +" OFFSET "+ size * page;
        query=String.format(query,"%"+key+"%","%"+key+"%","%"+key+"%");
        System.out.println(query);
        Query<Content> q=session.createNativeQuery(query,Content.class);
        return q.getResultList();
    }
}
