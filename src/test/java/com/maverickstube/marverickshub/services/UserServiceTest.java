package com.maverickstube.marverickshub.services;


import com.maverickstube.marverickshub.dtos.request.CreateUserRequest;
import com.maverickstube.marverickshub.dtos.response.CreateUserResponse;
import com.maverickstube.marverickshub.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testCreatUser(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("test@gmail.com");
        createUserRequest.setPassword("password");

        CreateUserResponse createUserResponse = userService.register(createUserRequest);

        assertNotNull(createUserResponse);
        assertTrue(createUserResponse.getMessage().contains("success"));
    }
    @Test
    @DisplayName("test that user can be retrieved from the db")
    @Sql(scripts ={"/db/data.sql"})
    public void testGetUserById(){
        User user = userService.getUserById(200L);
        assertNotNull(user);
    }

}
