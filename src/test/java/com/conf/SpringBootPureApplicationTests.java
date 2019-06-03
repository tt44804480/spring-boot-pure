package com.conf;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootPureApplicationTests {

	@Autowired
	private TransportClient transportClient;

	@Autowired
	private EsConfig esConfig;

	@Test
	public void contextLoads() {
		GetRequestBuilder getRequestBuilder = transportClient.prepareGet(esConfig.getStudentIndex(), esConfig.getStudentType(), "1");
		Map<String, Object> source = getRequestBuilder.execute().actionGet().getSource();
		System.out.println(source);
	}

}

