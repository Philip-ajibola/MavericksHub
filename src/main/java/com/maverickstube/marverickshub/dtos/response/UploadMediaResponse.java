package com.maverickstube.marverickshub.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maverickstube.marverickshub.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadMediaResponse {
    @JsonProperty("media_url")
    private String url;
    @JsonProperty("media_description")
    private String description;
    private Long mediaId;
    private Category category;
}
