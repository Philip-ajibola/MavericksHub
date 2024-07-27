package com.maverickstube.marverickshub.security.service;

import com.maverickstube.marverickshub.models.User;
import com.maverickstube.marverickshub.security.Models.SecureUser;
import com.maverickstube.marverickshub.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userService.getUserByUsername(username);
       return new SecureUser(user);
    }
}
