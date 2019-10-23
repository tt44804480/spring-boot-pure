package com.conf;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Queue;

/**
 *
 *
 * @author liutianyang
 * @since 1.0
 */
@Configuration
@EnableJms
public class ActivemqConfig {

    @Autowired
    private JmsProperties jmsProperties;

    private String queueName = "springboot-test-queue";

    @Bean
    public Queue queue(){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(queueName);
        return activeMQQueue;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(jmsProperties.isPubSubDomain());
        JmsProperties.Template template = jmsProperties.getTemplate();
        if (template.getDefaultDestination() != null) {
            jmsTemplate.setDefaultDestinationName(template.getDefaultDestination());
        }
        if (template.getDeliveryDelay() != null) {
            jmsTemplate.setDeliveryDelay(template.getDeliveryDelay());
        }
        jmsTemplate.setExplicitQosEnabled(template.determineQosEnabled());
        if (template.getDeliveryMode() != null) {
            jmsTemplate.setDeliveryMode(template.getDeliveryMode().getValue());
        }
        if (template.getPriority() != null) {
            jmsTemplate.setPriority(template.getPriority());
        }
        if (template.getTimeToLive() != null) {
            jmsTemplate.setTimeToLive(template.getTimeToLive());
        }
        if (template.getReceiveTimeout() != null) {
            jmsTemplate.setReceiveTimeout(template.getReceiveTimeout());
        }
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setSessionTransacted(true);
        jmsTemplate.setSessionAcknowledgeMode(JmsProperties.AcknowledgeMode.CLIENT.getMode());

        return jmsTemplate;
    }

    /**
     * 自定义ActiveMQConnectionFactory
     */
    @Component
    class ActiveMQConnectionFactoryCustomizer implements org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryCustomizer{

         @Override
         public void customize(ActiveMQConnectionFactory factory) {
             RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
             //重发次数,默认为6次   这里设置为10次
             redeliveryPolicy.setMaximumRedeliveries(3);
             //失败后 多少毫秒后开始重发
             redeliveryPolicy.setInitialRedeliveryDelay(1000);
             //重发的时间间隔
             redeliveryPolicy.setRedeliveryDelay(5000);
             //是否避免消息碰撞
             redeliveryPolicy.setUseCollisionAvoidance(true);
             factory.setRedeliveryPolicy(redeliveryPolicy);
         }
     }
}
