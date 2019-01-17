package com.conf.errorViewResolver;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/17
 */
@Component
public class MyErrorAttributes implements ErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",requestAttributes.getAttribute("javax.servlet.error.status_code",0));
        map.put("aa","aaaa");
        return map;
    }

    @Override
    public Throwable getError(RequestAttributes requestAttributes) {
        return null;
    }
}
