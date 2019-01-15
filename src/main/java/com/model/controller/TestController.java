package com.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/15
 */
@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @ResponseBody
    @RequestMapping("/test1")
    public Map<String,Object> test1(){
       Map<String,Object> map = new HashMap<>();
       map.put("a","");
       map.put("b",null);
       map.put("c",2);


        return map;
    }

}
