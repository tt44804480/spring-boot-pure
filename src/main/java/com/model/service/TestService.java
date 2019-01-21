package com.model.service;

import com.model.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/19
 */
@Service
public class TestService {

    @Autowired
    TestDao dao;

    public List<String> getNameById(String id){
        return dao.getNameById(id);
    }

    @Transactional
    public void updateName(String id,String name){
        dao.updateName(id,name);
        System.out.println(1/0);
    }
}
