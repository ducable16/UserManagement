package com.something.demo.factory.storage;

import com.something.demo.factory.request.BaseRequest;

public interface StorageFactory {

    public abstract void store(BaseRequest request);
}
