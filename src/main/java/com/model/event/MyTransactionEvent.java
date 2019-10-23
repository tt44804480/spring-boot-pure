package com.model.event;

import org.springframework.context.ApplicationEvent;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
public class MyTransactionEvent extends ApplicationEvent {

    private String name;

    public MyTransactionEvent(String name, Object source) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}