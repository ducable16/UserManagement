package com.something.demo.filter;

import com.something.demo.entity.User;
import com.something.demo.filter.category.*;
import com.something.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FilterWorkFlow {

    public AbstractFilter getFilter() {
        AbstractFilter nameFilter = new NameFilter();
        AbstractFilter ageFilter = new AgeFilter();
        AbstractFilter dateOfBirthFilter = new DateOfBirthFilter();
        AbstractFilter createDateFilter = new CreateDateFilter();
        AbstractFilter roleFilter = new RoleFilter();

        nameFilter.setNext(ageFilter);
        ageFilter.setNext(dateOfBirthFilter);
        dateOfBirthFilter.setNext(createDateFilter);
        createDateFilter.setNext(roleFilter);

        return nameFilter;
    }
}
