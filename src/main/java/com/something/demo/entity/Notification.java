package com.something.demo.entity;

import java.io.Serializable;
import java.util.List;

public class Notification implements Serializable {
    private String sender;
    private List<Role> receiver_roles;
    private String title;
    private String content;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<Role> getReceiver_roles() {
        return receiver_roles;
    }

    public void setReceiver_roles(List<Role> receiver_roles) {
        this.receiver_roles = receiver_roles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
