package com.maverickstube.marverickshub.dtos.response;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class UserResponse {
    private Long id;
    @Column(unique=true)
    private String email;
    private LocalDateTime timeCreated;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeUpdated;
}
