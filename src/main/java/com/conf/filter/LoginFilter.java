package com.conf.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
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

        //进行登录验证
        servletRequest.getRequestDispatcher("/").forward(servletRequest,servletResponse);
        //filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("login filter destroy");
    }
}
