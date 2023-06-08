package com.github.client;

import cn.hutool.core.lang.UUID;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName ESClient
 * @Description 本地es客户端
 * @Author lcz
 * @Date 2023-06-07 19:17:05
 * @Version 1.0
 */
public class ESClient {
    public static void main(String[] args) throws IOException {
        //创建连接
        RestHighLevelClient esc = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        /*CreateIndexRequest indexRequest = new CreateIndexRequest("user");
        CreateIndexResponse indexResponse = esc.indices().create(indexRequest, RequestOptions.DEFAULT);
        System.out.println("创建索引："+indexResponse.isAcknowledged());*/

      /*  GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse response = esc.indices().get(request, RequestOptions.DEFAULT);
        System.out.println(response.getAliases());
        System.out.println(response.getSettings());
        System.out.println(response.getMappings());*/



        /*BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest().index("user").id(UUID.randomUUID().toString()).source(XContentType.JSON,"name","wzj","age",16,"sex","女"));
        bulkRequest.add(new IndexRequest().index("user").id(UUID.randomUUID().toString()).source(XContentType.JSON,"name","sc","age",25,"sex","男"));
        bulkRequest.add(new IndexRequest().index("user").id(UUID.randomUUID().toString()).source(XContentType.JSON,"name","wza","age",27,"sex","男"));
        bulkRequest.add(new IndexRequest().index("user").id(UUID.randomUUID().toString()).source(XContentType.JSON,"name","xt","age",20,"sex","女"));
        BulkResponse bulk = esc.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.getIngestTookInMillis());
        System.out.println(Arrays.toString(bulk.getItems()));*/

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user")
                .source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 30)));

        SearchResponse searchResponse = esc.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsString());
        }


        //关闭连接
        esc.close();
    }
}
