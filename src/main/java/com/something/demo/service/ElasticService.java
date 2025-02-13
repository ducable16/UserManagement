package com.something.demo.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.something.demo.entity.Post;
import com.something.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ElasticService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void savePost(Post post) {
        postRepository.save(post);
    }

//    public List<Post> searchPost(String keyword) {
//        return postRepository.findByContent(keyword);
//    }

    public List<Post> allPost() {
        return postRepository.findAll();
    }
    public List<Post> search(String text) {

        Query query = QueryBuilders.match(q -> q
                .field("content")
                .query(text)
                .fuzziness("AUTO"));


        NativeQuery nativeQuery = new NativeQueryBuilder()
                .withQuery(query)
                .build();

        SearchHits<Post> searchHits = elasticsearchOperations.search(nativeQuery, Post.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

}
