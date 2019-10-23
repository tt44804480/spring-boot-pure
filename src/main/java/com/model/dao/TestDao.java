package com.model.dao;

import com.model.base.BaseMappers;
import com.model.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/19
 */
@Repository
public interface TestDao extends BaseMappers<Student>{

    public List<String> getNameById(String id);

    public void updateName(@Param("id") String id, @Param("name") String name);
}
