package com.something.demo.repository;

import com.something.demo.entity.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostRepository extends ElasticsearchRepository<Post, String> {
        List<Post> findByTitle(String title);

        List<Post> findByAuthor(String author);

        List<Post> findByContent(String content);

        List<Post> findAll();
}
