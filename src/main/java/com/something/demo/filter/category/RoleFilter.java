package com.something.demo.filter.category;

import com.something.demo.entity.User;
import com.something.demo.filter.AbstractFilter;
import com.something.demo.request.FilterRequest;

import java.util.List;

public class RoleFilter extends AbstractFilter {

    @Override
    protected List<User> doFiltering(FilterRequest filterRequest, List<User> userList) {
        if(filterRequest.getRole() != null) {
            int i = 0;
            while (i < userList.size()) {
                if(userList.get(i).getRole() != filterRequest.getRole()) {
                    userList.remove(i);
                }
                else i++;
            }
        }
        return userList;
    }
}
