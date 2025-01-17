package com.something.demo.factory;

import org.springframework.stereotype.Component;

@Component
public class DatabaseStorageFactory extends StorageFactory {

    @Override
    public DataStorage createStorage() {
        return new DatabaseStorage();
    }
}