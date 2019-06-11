package com.conf;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootPureApplicationTests {

	@Autowired
	private TransportClient transportClient;

	@Autowired
	private EsConfig esConfig;

	/**
	 * 通过id获取文档
	 */
	@Test
	public void test1() {
		GetRequestBuilder getRequestBuilder = transportClient.prepareGet(esConfig.getStudentIndex(), esConfig.getStudentType(), "7");
		GetResponse documentFields = getRequestBuilder.execute().actionGet();
		System.out.println(documentFields.getSource());
	}

	/**
	 * 通过id新增文档
	 */
	@Test
	public void test2(){
		Map<String,Object> map = new HashMap<>();
		map.put("name","小灰");
		map.put("age",25);
		map.put("address","地球");
		map.put("interesting","吃饭 跳舞");
		IndexRequestBuilder indexRequestBuilder = transportClient.prepareIndex(esConfig.getStudentIndex(), esConfig.getStudentType(), "7");
		IndexResponse indexResponse = indexRequestBuilder.setSource(map).get();
		System.out.println(indexResponse.status().getStatus());
	}

	/**
	 * 通过id删除
	 */
	@Test
	public void test3(){
		DeleteRequestBuilder deleteRequestBuilder = transportClient.prepareDelete(esConfig.getStudentIndex(), esConfig.getStudentType(), "7");
		DeleteResponse deleteResponse = deleteRequestBuilder.get();
		System.out.println(deleteResponse.status());
	}

	/**
	 * 根据id修改
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException {
		XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
		xContentBuilder.startObject().field("name","小绵羊").endObject();

		UpdateRequestBuilder updateRequestBuilder = transportClient.prepareUpdate(esConfig.getStudentIndex(), esConfig.getStudentType(), "1");
		UpdateResponse updateResponse = updateRequestBuilder.setDoc(xContentBuilder).get();
		System.out.println(updateResponse);
	}

	/**
	 * upset
	 */
	@Test
	public void test5() throws IOException, ExecutionException, InterruptedException {
		IndexRequest indexRequest = new IndexRequest()
				.index(esConfig.getStudentIndex())
				.type(esConfig.getStudentType())
				.id("7")
				.source(XContentFactory.jsonBuilder().startObject()
						.field("name","哇哈哈")
						.field("age",24)
						.field("address","address")
						.field("interesting","interesting")
						.endObject()
				);

		UpdateRequest updateRequest = new UpdateRequest();
		XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
		xContentBuilder.startObject().field("name","小绵羊").endObject();
		updateRequest.index(esConfig.getStudentIndex())
						.type(esConfig.getStudentType())
						.id("7")
						.doc(xContentBuilder)
						.upsert(indexRequest);
		transportClient.update(updateRequest).get();
	}

	/**
	 * mget
	 */
	@Test
	public void test6(){
		MultiGetRequestBuilder multiGetRequestBuilder = transportClient.prepareMultiGet();
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		MultiGetResponse multiGetItemResponses = multiGetRequestBuilder.add(esConfig.getStudentIndex(), esConfig.getStudentType(),list).get();
		MultiGetItemResponse[] responses = multiGetItemResponses.getResponses();
		for(MultiGetItemResponse multiGetItemResponse : responses){
			System.out.println(multiGetItemResponse.getResponse().getSource());
		}
	}

	/**
	 * match_all
	 */
	@Test
	public void test7(){
		SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(esConfig.getStudentIndex());
		SearchResponse searchResponse = searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery())
				.setSize(2).get();

		System.out.println(searchResponse.getHits().getTotalHits());
		SearchHit[] hits = searchResponse.getHits().getHits();
		for(SearchHit searchHit : hits){
			System.out.println(searchHit.getSourceAsMap());
		}
	}

	/**
	 * match
	 */
	@Test
	public void test8(){
		SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(esConfig.getStudentIndex());
		SearchResponse searchResponse = searchRequestBuilder.setQuery(QueryBuilders.matchQuery("interesting", "睡觉")).get();
		SearchHit[] hits = searchResponse.getHits().getHits();
		for(SearchHit searchHit : hits){
			System.out.println(searchHit.getSourceAsMap());
		}
	}

	/**
	 *  multi_match：
	 */
	@Test
	public void test9(){
		SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(esConfig.getStudentIndex());
		MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("吃饭", "interesting");
		SearchResponse searchResponse = searchRequestBuilder.setQuery(multiMatchQueryBuilder).get();
		SearchHit[] hits = searchResponse.getHits().getHits();
		for(SearchHit searchHit : hits){
			System.out.println(searchHit.getSourceAsMap());
		}
	}







}

