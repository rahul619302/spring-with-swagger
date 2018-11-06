package com.cg.SpringWithSwagger.daos;

import com.cg.SpringWithSwagger.pojos.User;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao implements ITestDao{
    @Override
    public User getUser() {
        return new User(1, "Rohan Singh");
    }
}
