package com.conf;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/19
 */
@Configuration
public class DruidConfig {

    @Autowired
    WallFilter wallFilter;

    /**
     * 注册我们自己的数据源
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        DruidDataSource druidDataSource = new DruidDataSource();

//在设置之前先判断是都已经存在WallConfig，如果有，直接将现有的替换掉

        List<Filter> filterList = new ArrayList<Filter>();
        List<Filter> filters = druidDataSource.getProxyFilters();

        boolean isExist = false;

        for(Filter filter:filters){

            if(filter instanceof WallFilter){

                ((WallFilter)filter).setConfig(wallConfig());

                isExist = true;

            }

        }

        if(!isExist){
            filterList.add(wallFilter);
            druidDataSource.setProxyFilters(filterList);

        }
        return druidDataSource;
    }

    /**
     * 配置druid监控
     * 配置一个管理后台的Servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","1234");
        initParams.put("allow","");//默认就是允许所有访问
        initParams.put("deny","192.168.15.21");//禁止谁访问
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置一个web监控的filter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

    @Bean(name = "wallFilter")
    @DependsOn("wallConfig")
    public WallFilter wallFilter(WallConfig wallConfig) {

        WallFilter wallFilter = new WallFilter();

        wallFilter.setConfig(wallConfig);

        return wallFilter;

    }


    @Bean(name = "wallConfig")
    public WallConfig wallConfig() {

        WallConfig wallConfig = new WallConfig();

        wallConfig.setMultiStatementAllow(true);// 允许一次执行多条语句

        wallConfig.setNoneBaseStatementAllow(true);// 允许一次执行多条语句

        return wallConfig;

    }

}
