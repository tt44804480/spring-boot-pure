package com.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
@RestController
@RequestMapping("/activemq")
public class TestActivemqController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @RequestMapping("/queue/send")
    public String sendQueue(Session session) throws JMSException {
        jmsMessagingTemplate.convertAndSend(queue, "abc");
        logger.info("发送消息成功");

        session.rollback();
        return "success";
    }

}
