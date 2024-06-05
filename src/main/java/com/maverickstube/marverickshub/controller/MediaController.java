package com.maverickstube.marverickshub.controller;

import com.maverickstube.marverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.marverickshub.services.MediaService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.processing.SQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/media")
@AllArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadMedia(@ModelAttribute UploadMediaRequest uploadMediaRequest){
        return ResponseEntity.status(CREATED).body(mediaService.upload(uploadMediaRequest));
    }
}
