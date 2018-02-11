package com.first.angular.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class BasePandaDao {

    @Autowired
    private MongoTemplate template;
}
