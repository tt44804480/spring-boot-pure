package com.model.service;

import com.model.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * drools测试service
 *
 * @author liutianyang
 * @since 1.0
 */
@Transactional
@Service
public class DroolsService {

    @Autowired
    private TestDao dao;

}
