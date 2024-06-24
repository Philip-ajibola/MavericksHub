package com.maverickstube.marverickshub.repository;

import com.maverickstube.marverickshub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.email=:email")
    Optional<User> findByEmail(String email);
}
