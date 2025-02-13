package com.something.demo.config;

import co.elastic.clients.elasticsearch.nodes.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.net.UnknownHostException;

//@Configuration
//public class ElasticSearchConfig {
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
//        return new ElasticsearchTemplate(getClient());
//    }
//
//    @Bean
//    public Client getClient() throws UnknownHostException {
//        Settings setting = Settings
//                .builder()
//                .put("client.transport.sniff", true)
//                .put("path.home", "/usr/share/elasticsearch") //elasticsearch home path
//                .put("cluster.name", "elasticsearch")
//                .build();
//        //please note that client port here is 9300 not 9200!
//        TransportClient client = new PreBuiltTransportClient(setting)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        return client;
//    }
//}
