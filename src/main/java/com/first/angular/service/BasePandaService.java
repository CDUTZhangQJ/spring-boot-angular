package com.first.angular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.angular.dao.BasePandaDao;

@Service
public class BasePandaService {

    @Autowired
    private BasePandaDao dao;

}
