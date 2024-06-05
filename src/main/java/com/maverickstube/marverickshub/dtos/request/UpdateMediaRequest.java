package com.maverickstube.marverickshub.dtos.request;

import com.maverickstube.marverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMediaRequest {
    private Long mediaId;
    private String description = "";
    private Category category = Category.NONE;
}
