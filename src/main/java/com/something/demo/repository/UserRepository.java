package com.something.demo.repository;

import com.something.demo.entity.User;
import com.something.demo.request.LoginRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findById(ObjectId id);

    Optional<User> deleteById(ObjectId id);

    Optional<User> findByUsername(String account);

}
