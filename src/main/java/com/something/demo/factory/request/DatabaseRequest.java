package com.something.demo.factory.request;

import com.something.demo.entity.User;

public class DatabaseRequest extends BaseRequest {
    User user;

    public DatabaseRequest() {}

    public DatabaseRequest(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void encodePassword(String password) {
        this.user.setPassword(password);
    }
    public void setUser(User user) {
        this.user = user;
    }
}
