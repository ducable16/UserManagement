package com.something.demo.repository;

import com.something.demo.entity.ChatMessage;
import com.something.demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, Long> {
}
