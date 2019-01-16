package com.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/15
 */
@SpringBootApplication
@ComponentScan("com.model")
@ComponentScan("com.conf")
public class ApplicationContext
{
    public static void main(String[] args) {
        SpringApplication.run(ApplicationContext.class, args);
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
