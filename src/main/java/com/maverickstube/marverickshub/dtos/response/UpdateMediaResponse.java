package com.maverickstube.marverickshub.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maverickstube.marverickshub.models.Category;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
@Setter
@Getter
public class UpdateMediaResponse {
    private Long id;
    private String url;
    private String description;
    private Category category;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}
