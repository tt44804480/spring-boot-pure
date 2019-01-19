package com.conf.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/18
 */
public class LoginFilter implements Filter{
    Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("login filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("login filter doFilter");
        boolean status = true;

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestURI = httpServletRequest.getRequestURI();
        if(requestURI!=null && requestURI.contains("druid")){
            filterChain.doFilter(servletRequest,servletResponse);
            status = false;
        }

        if(status){
            //进行登录验证
            httpServletResponse.sendRedirect("/");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("login filter destroy");
    }
}
