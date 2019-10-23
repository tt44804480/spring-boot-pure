package com.model.service;

import com.model.dao.TestDao;
import com.model.entity.Address;
import com.model.entity.Student;
import com.model.event.MyTransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/19
 */
@Service
public class TestService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TestDao dao;

    @Autowired
    private ApplicationContext applicationContext;

    public List<String> getNameById(String id){
        return dao.getNameById(id);
    }

    @Transactional
    public void updateName(String id,String name){
        dao.updateName(id,name);
    }

    @Transactional
    public void testTransactionalEventListener(){
        logger.info("service  开始。。。");
        //System.out.println(1/0);
        applicationContext.publishEvent(new MyTransactionEvent("test",applicationContext));
    }

    @Transactional
    public List<Student> testBaseMapper(){
        Example example = new Example(Student.class);
        example.createCriteria().andEqualTo("name", "小明");
        List<Student> students = dao.selectByExample(example);
        return students;
    }

    public void testInsertBaseMapper(){
        Address address = new Address("province", "city", "name");
        Student student = new Student();
        student.setName("哇哈哈哈哈哈");
        student.setAge(25);
        student.setAddress(address);
        dao.insert(student);
    }
}
