package com.conf;

import com.conf.filter.LoginFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Arrays;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/15
 */
@SpringBootApplication
@ComponentScan("com.model")
@ComponentScan("com.conf")
@MapperScan(value = "com.model.dao")
public class ApplicationContext
{
    public static void main(String[] args) {
        SpringApplication.run(ApplicationContext.class, args);
    }

    /**
     * 国际化资源组件
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    /**
     * 嵌入式的servlet容器定制器组件
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new MyEmbeddedServletContainerCustomizer();
    }

    /**
     * 添加filter 过滤所有的html页面（进行简单的登录验证）
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.setUrlPatterns(Arrays.asList("*.html"));
        return registrationBean;
    }


}
