package com.cg.SpringWithSwagger.test.service;

import com.cg.SpringWithSwagger.daos.ITestDao;
import com.cg.SpringWithSwagger.pojos.User;
import com.cg.SpringWithSwagger.request.Request;
import com.cg.SpringWithSwagger.response.Response;
import com.cg.SpringWithSwagger.services.TestService;
import com.cg.SpringWithSwagger.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TestServiceTest {

    private Request request;
    private String response;
    @InjectMocks
    private TestService testService;
    @InjectMocks
    private ObjectMapper objectMapper;
    @Mock
    private ITestDao testDao;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        request=new Request("1", "Rahul Singh", "");
        Map<String, Object> map = new LinkedHashMap<>();
        User user = new User(Integer.valueOf(request.getParam1()), request.getParam2());
        Response response=new Response(Constants.S200, Constants.S200.getMessage());
        response.setMap(map);
        map.put("user", user);
        this.response=objectMapper.writeValueAsString(response);
        given(testDao.getUser()).willReturn(user);
    }

    @Test
    void getUser() throws JsonProcessingException {
        Response response=testService.getUser(request);
        assertThat(this.response, is(objectMapper.writeValueAsString(response)));
    }
}