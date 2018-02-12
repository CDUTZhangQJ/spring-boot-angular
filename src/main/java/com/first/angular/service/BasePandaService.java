package com.first.angular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.angular.dao.BasePandaDao;
import com.first.angular.entity.Panda;

@Service
public class BasePandaService {

    @Autowired
    private BasePandaDao dao;

    public List<Panda> findAll() {
        return dao.findAll();
    }

    public void save(String name) {
        dao.save(name);
    }

}
