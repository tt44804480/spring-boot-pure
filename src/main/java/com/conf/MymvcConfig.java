package com.conf;

import com.conf.interceptor.LoginHandlerInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/15
 */
@Component
public class MymvcConfig extends WebMvcConfigurerAdapter {

    /**
     * @param registry
     * 可以设置一些固定的响应页面
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/upload/");
    }

    /**
     * 添加上传的图片显示的路径映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:E:/work-space/zkhz_springboot/src/main/resources/static/upload/");
        //registry.addResourceHandler("/upload/**").addResourceLocations("/upload/");
    }
}
