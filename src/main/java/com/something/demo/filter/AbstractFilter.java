package com.something.demo.filter;

import com.something.demo.entity.User;
import com.something.demo.request.FilterRequest;

import java.util.List;

public abstract class AbstractFilter {
    public AbstractFilter nextFilter;

    public List<User> doFilter(FilterRequest filterRequest, List<User> userList) {
        List<User> filteredUserList = this.doFiltering(filterRequest, userList);
        if (nextFilter != null) {
            return nextFilter.doFiltering(filterRequest, filteredUserList);
        }
        return filteredUserList;
    }

    public void setNext(AbstractFilter filter) {
        this.nextFilter = filter;
    }

    protected abstract List<User> doFiltering(FilterRequest filterRequest, List<User> userList);
}
