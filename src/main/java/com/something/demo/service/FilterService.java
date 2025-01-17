package com.something.demo.service;

import com.something.demo.entity.User;
import com.something.demo.request.FilterRequest;
import com.something.demo.filter.FilterWorkFlow;
import com.something.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class FilterService {

    @Autowired
    UserRepository userRepository;

    public List<User> findUserByFilter(@RequestBody FilterRequest filterRequest) {
        List<User> users = userRepository.findAll();
        FilterWorkFlow filterWorkFlow = new FilterWorkFlow();
        return filterWorkFlow.getFilter().doFilter(filterRequest, users);
    }
}
