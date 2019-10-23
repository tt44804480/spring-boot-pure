package com.model.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * 队列的监听
 *
 * @author liutianyang
 * @since 1.0
 */
@Component
public class Queue_Consumer {
    Logger logger = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = "springboot-test-queue")
    public void receive(TextMessage textMessage) throws JMSException {
        logger.info("消息为*********"+textMessage.getText());
    }

}
