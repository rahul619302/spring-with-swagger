package com.cg.SpringWithSwagger.services;

import com.cg.SpringWithSwagger.daos.ITestDao;
import com.cg.SpringWithSwagger.pojos.User;
import com.cg.SpringWithSwagger.request.Request;
import com.cg.SpringWithSwagger.response.Response;
import com.cg.SpringWithSwagger.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TestService implements ITestService{

    @Autowired
    private ITestDao testDao;

    @Override
    public Response getUser(Request request) {
        User user = new User(Integer.valueOf(request.getParam1()), request.getParam2());
        Response response = new Response(Constants.S200, Constants.S200.getMessage());
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user", user);
        response.setMap(map);
        User user1=testDao.getUser();
        return response;
    }

}
