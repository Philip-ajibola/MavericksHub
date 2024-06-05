package com.maverickstube.marverickshub.utils;

import com.maverickstube.marverickshub.dtos.request.UploadMediaRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.maverickstube.marverickshub.models.Category.ACTION;

public class TestUtil {
    public  static final String TEST_IMAGE_LOCATION = "C:\\Users\\Dell\\Desktop\\marverickshub\\src\\main\\resources\\static\\thanos.jpeg";
    public static final  String TEST_VIDEO_LOCATION = "C:\\Users\\Dell\\Desktop\\marverickshub\\src\\main\\resources\\static\\vid1.mp4"  ;

    public static UploadMediaRequest getUploadMediaRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest uploadRequest = new UploadMediaRequest();

        MultipartFile file = new MockMultipartFile("video", inputStream);
        uploadRequest.setMediaFile(file);
        uploadRequest.setCategory(ACTION);
        uploadRequest.setUserId(200L);
        return uploadRequest;
    }
}
