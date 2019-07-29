package com.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 测试mongodb controller
 *
 * @author liutianyang
 * @since 1.0
 */
@RestController
@RequestMapping("/mongodb")
public class TestMongodbController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/find")
    public Object find(){
        Query query = new Query();
        //query.addCriteria(Criteria.where("name").is("酒仙"));
        List<Map> maps = mongoTemplate.find(query, Map.class,"suts");
        return maps;
    }
}
