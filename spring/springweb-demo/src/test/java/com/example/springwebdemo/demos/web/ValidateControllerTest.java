package com.example.springwebdemo.demos.web;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
class ValidateControllerTest {

    @Resource
    MockMvc mockMvc;

    @Test
    void add() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/validate/user/add")
                .content(getClass().getClassLoader().getResourceAsStream("valid/user/add-user.json").readAllBytes())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        int status = result.getResponse().getStatus();
        System.out.println(status);
        String resp = result.getResponse().getContentAsString();
        System.out.println(resp);
    }

    @Test
    void update() {
    }

    @Test
    void del() {
    }

    @Test
    void query() {
    }
}