package com.model.controller;

import com.github.pagehelper.PageHelper;
import com.model.entity.Student;
import com.model.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/15
 */
@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TestService service;

    @ResponseBody
    @RequestMapping("/test1")
    public Map<String,Object> test1(){
       Map<String,Object> map = new HashMap<>();
       map.put("a","");
       map.put("b",null);
       map.put("c",2);
        return map;
    }

    @ResponseBody
    @RequestMapping("/test2")
    public String test2(String name){
        if(name == null){
            throw  new RuntimeException("哇哈哈哈哈哈哈哈");
        }

        return "成功";
    }

    @ResponseBody
    @RequestMapping("/test3")
    public List<Map<String, Object>> test3(){
        String sql = "select * from customer limit 1";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        System.out.println(mapList);
        return mapList;
    }

    @ResponseBody
    @RequestMapping("/getNameById")
    public List<String> getNameById(String id){
        logger.info("我是getNameById");
        PageHelper.startPage(1,10);
        List<String> list = service.getNameById(id);
        return list;
    }

    @ResponseBody
    @RequestMapping("/updateName")
    public String updateName(String id,String name){
        service.updateName(id,name);
        return "success";
    }

    @ResponseBody
    @GetMapping("/test/transactionalEventListener")
    public void testTransactionalEventListener(){
        service.testTransactionalEventListener();
    }

    @ResponseBody
    @GetMapping("/test/basemapper")
    public List<Student> testBaseMapper(){
        return service.testBaseMapper();
    }

    @ResponseBody
    @GetMapping("/test/insert/base/mapper")
    public void testInsertBaseMapper(){
        service.testInsertBaseMapper();
    }

}
