package com.maverickstube.marverickshub.dtos.request;

import com.maverickstube.marverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class UploadMediaRequest {
    private long userId;
    private MultipartFile mediaFile;
    private String description ;
    private Category category;
}
