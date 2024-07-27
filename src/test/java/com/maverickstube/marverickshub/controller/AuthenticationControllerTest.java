package com.maverickstube.marverickshub.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverickstube.marverickshub.dtos.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mock;
    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void authenticateUserTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("john2@gmail.com");
        loginRequest.setPassword("password");
        ObjectMapper objectMapper = new ObjectMapper();
        mock.perform(post("/api/v1/auth")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(loginRequest)))
                .andExpect(status().isOk())
                .andDo(print());

    }
@Test
    @Sql(scripts = {"/db/data.sql"})
    public void unAuthenticateUserTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("john2@gmail.com");
        loginRequest.setPassword("wrongPassword");
        ObjectMapper objectMapper = new ObjectMapper();
        mock.perform(post("/api/v1/auth")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andDo(print());

    }

}
