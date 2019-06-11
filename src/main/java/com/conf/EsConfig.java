package com.conf;

import lombok.Data;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * elasticsearch 配置
 *
 * @author liutianyang
 * @since 1.0
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "elasticsearch")
@PropertySource(value = {"classpath:esconfig.properties"})
public class EsConfig {

    /**
     * elk集群地址
     */
    private String hostName;
    /**
     * 端口
     */
    private String port;
    /**
     * 集群名称
     */
    private String clusterName;
    /**
     * 连接池
     */
    private String poolSize;

    private String studentIndex;

    private String studentType;

    @Bean
    public TransportClient init() throws UnknownHostException {

        TransportClient transportClient;
        // 配置信息
        Settings esSetting = Settings.builder()
                .put("cluster.name", clusterName)
                .put("node.name", "nodeName")
                // 增加嗅探机制，找到ES集群
                .put("client.transport.sniff", true)
                // 增加线程池个数，暂时设为5
                .put("thread_pool.search.size", Integer.parseInt(poolSize))
                .build();

        transportClient = new PreBuiltTransportClient(esSetting);
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port));
        transportClient.addTransportAddresses(transportAddress);

        return transportClient;
    }
}
