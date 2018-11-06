package com.cg.SpringWithSwagger.controllers;

import com.cg.SpringWithSwagger.pojos.User;
import com.cg.SpringWithSwagger.request.Request;
import com.cg.SpringWithSwagger.response.Response;
import com.cg.SpringWithSwagger.services.ITestService;
import com.cg.SpringWithSwagger.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @GetMapping("hello")
    public String sayHello() {
        return "Hello World!";
    }

    @PostMapping("users/post")
    public Response getUser(@RequestBody Request request) {
        Response response=testService.getUser(new Request("1", "Rahul Singh", ""));
        //Response response = getResponse(request);
        return response;
    }

    private Response getResponse(@RequestBody Request request) {
        Map<String, Object> map = new LinkedHashMap<>();
        User user = new User(Integer.valueOf(request.getParam1()), request.getParam2());
        Response response = new Response(Constants.S200, Constants.S200.getMessage());
        response.setMap(map);
        map.put("user", user);
        return response;
    }

}
