package com.something.demo.request;

import com.something.demo.entity.Role;

public class UpdateUserRequest {
    private String name;
    private String dateOfBirth;
    private int age;
    private String username;
    private String password;
    private Role role;
    private String createDate;

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setCreateDate(String createDate) {this.createDate = createDate;}

    public String getCreateDate() {return createDate;}
}
