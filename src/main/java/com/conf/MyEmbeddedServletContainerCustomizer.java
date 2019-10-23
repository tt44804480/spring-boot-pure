package com.conf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * @author liutianyang
 * @create 2019-01-2019/1/18
 */
public class MyEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer{

    /**
     * 嵌入式的servlet容器定制器
     * @param configurableEmbeddedServletContainer
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        //configurableEmbeddedServletContainer.setPort(8088);
    }
}
