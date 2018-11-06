package com.cg.SpringWithSwagger.test.controller;

import com.cg.SpringWithSwagger.controllers.TestController;
import com.cg.SpringWithSwagger.pojos.User;
import com.cg.SpringWithSwagger.request.Request;
import com.cg.SpringWithSwagger.response.Response;
import com.cg.SpringWithSwagger.services.ITestService;
import com.cg.SpringWithSwagger.util.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TestControllerTest {

    @Mock
    private ITestService testService;
    @InjectMocks
    private TestController controller;
    @InjectMocks
    private ObjectMapper objectMapper;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    private MockMvc mockmvc;
    private Request request;
    private String response;
    private Response response1;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
        request = new Request("1", "Rahul Singh", "");
        Map<String, Object> map = new LinkedHashMap<>();
        User user = new User(Integer.valueOf(request.getParam1()), request.getParam2());
        response1 = new Response(Constants.S200, Constants.S200.getMessage());
        response1.setMap(map);
        map.put("user", user);
        this.response = objectMapper.writeValueAsString(response1);
    }

    @Test
    void sayHello() throws Exception {
        this.response = "Hello World!";
        MvcResult result = mockmvc.perform(get("/test/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertThat(this.response, is(response));
    }

    @Test
    void getUser() throws Exception {
        rule.strictness(Strictness.LENIENT);
        given(testService.getUser(new Request("1", "Rahul Singh", ""))).
                willReturn(new Response(Constants.S200, Constants.S200.getMessage()));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        byte[] requestByte= objectMapper.writeValueAsBytes(this.request);
        MvcResult result = mockmvc.perform(post("/test/users/post")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestByte)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertThat(this.response, is(response));
    }
}