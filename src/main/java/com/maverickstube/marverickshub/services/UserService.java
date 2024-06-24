package com.maverickstube.marverickshub.services;

import com.maverickstube.marverickshub.dtos.request.CreateUserRequest;
import com.maverickstube.marverickshub.dtos.response.CreateUserResponse;
import com.maverickstube.marverickshub.models.User;

public interface UserService {
    CreateUserResponse register(CreateUserRequest request);

    User getUserById(Long id);
    User getUserByUsername(String email);
}
