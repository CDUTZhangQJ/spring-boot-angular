package com.first.angular.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.angular.entity.Panda;
import com.first.angular.service.BasePandaService;

@RestController
@RequestMapping("/panda")
public class PandaController {

    Logger logger = LogManager.getLogger(PandaController.class);

    @Autowired
    private BasePandaService basePandaService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void add(@RequestParam String name) {
        basePandaService.save(name);
    }

    /**
     * 取得所有的数据.
     * 
     * @return List
     */
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Panda> getAllPanda() {
        logger.info("get all panda begin\t");
        List<Panda> pandas = basePandaService.findAll();
        return pandas;
    }
}
