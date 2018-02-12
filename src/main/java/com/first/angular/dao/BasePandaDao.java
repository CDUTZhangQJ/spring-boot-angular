package com.first.angular.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.first.angular.entity.Panda;

@Component
public class BasePandaDao {

    @Autowired
    private MongoTemplate template;

    public List<Panda> findAll() {
        return template.find(new Query(), Panda.class);
    }

    public void save(String name) {
        template.save(name);
    }
}
