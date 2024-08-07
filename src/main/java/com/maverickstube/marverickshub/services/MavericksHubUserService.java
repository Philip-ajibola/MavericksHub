package com.maverickstube.marverickshub.services;

import com.cloudinary.utils.ObjectUtils;
import com.maverickstube.marverickshub.dtos.request.CreateUserRequest;
import com.maverickstube.marverickshub.dtos.response.CreateUserResponse;
import com.maverickstube.marverickshub.exception.UserNotFoundException;
import com.maverickstube.marverickshub.models.Authority;
import com.maverickstube.marverickshub.models.User;
import com.maverickstube.marverickshub.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class MavericksHubUserService implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public MavericksHubUserService(ModelMapper modelMapper,PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserResponse register(CreateUserRequest request) {
       User user =  modelMapper.map(request, User.class);
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setAuthorities(new HashSet<>());
       user.getAuthorities().add(Authority.USER);
       user = userRepository.save(user);
       var response = modelMapper.map(user,CreateUserResponse.class);
       response.setMessage("user registered successfully");
        return response;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Override
    public User getUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
    }
}
