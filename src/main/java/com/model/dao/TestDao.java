package com.model.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/19
 */
@Repository
public interface TestDao {

    public List<String> getNameById(String id);
}
