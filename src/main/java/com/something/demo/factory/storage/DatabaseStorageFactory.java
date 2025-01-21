package com.something.demo.factory.storage;

import com.something.demo.factory.request.BaseRequest;
import com.something.demo.factory.request.DatabaseRequest;
import com.something.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseStorageFactory implements StorageFactory {

    @Autowired
    UserRepository userRepository;

    public void store(BaseRequest request) {
        if(request instanceof DatabaseRequest) {
            DatabaseRequest dbrequest = (DatabaseRequest) request;
            dbrequest.encodePassword(dbrequest.getUser().getPassword());
            userRepository.save(dbrequest.getUser());
        }
    }
}