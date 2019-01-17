package com.conf.errorViewResolver;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/17
 */
@Component
public class MyErrorViewResolver implements ErrorViewResolver{

    /**
     * 这里也可以自己写一个ErrorViewResolver，交给模板引擎去渲染
     * 当出错的时候就会调用这个方法生成对应的ModelAndView
     * @param request
     * @param status
     * @param model
     * @return
     */
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/errorFile/errorfile");
        System.out.println(model);
        System.out.println(status);
        mav.addAllObjects(model);
        return mav;
    }
}
