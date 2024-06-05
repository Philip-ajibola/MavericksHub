package com.maverickstube.marverickshub.services;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.maverickstube.marverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.marverickshub.dtos.response.MediaResponse;
import com.maverickstube.marverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.marverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.marverickshub.models.Category;
import com.maverickstube.marverickshub.models.Media;
import com.maverickstube.marverickshub.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.maverickstube.marverickshub.models.Category.ACTION;
import static com.maverickstube.marverickshub.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("/db/data.sql")
    public void uploadMediaTest(){
        Path path = Paths.get(TEST_IMAGE_LOCATION);
        try(var inputStream= Files.newInputStream(path)) {
            UploadMediaRequest uploadRequest = getUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(uploadRequest);

            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        }catch (IOException e){
            assertThat(e).isNull();
        }
    }
    @Test
    public void getMediaForUserTest(){
        Long userId = 201L;
        List<MediaResponse> userMedias = mediaService.getUserMedias(userId);
        assertThat(userMedias).hasSize(2);
    }
    @Test
    @Sql("/db/data.sql")
    public void uploadMediaTest1(){
        Path path = Paths.get(TEST_VIDEO_LOCATION);
        try(var inputStream= Files.newInputStream(path)) {
            UploadMediaRequest uploadRequest = getUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(uploadRequest);

            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        }catch (IOException e){
            assertThat(e).isNull();
        }
    }
    @Test
    @Sql("/db/data.sql")
    public void updateMediaTest() throws JsonPointerException {
        List<JsonPatchOperation> operations = List.of(
                new ReplaceOperation(new JsonPointer("/category"),new TextNode(ACTION.name()))
        );
        JsonPatch request = new JsonPatch(operations);
        Media media = mediaService.getMediaBy(101L);
        assertThat(media.getCategory().equals(Category.HORROR));
        UpdateMediaResponse response = mediaService.update(101L,request);
        media = mediaService.getMediaBy(101L);
        assertThat(media.getCategory().equals(ACTION));
    }
    @Test
    @DisplayName("test update media file ")
    @Sql("/db/data.sql")
    public void testPartialUpdateMediaFile() throws JsonPointerException {
        List<JsonPatchOperation> operations = List.of(
                new ReplaceOperation(new JsonPointer("/category"),new TextNode(ACTION.name()))
        );
        JsonPatch request = new JsonPatch(operations);
        Category category = mediaService.getMediaBy(103L).getCategory();
        assertThat(category).isNotEqualTo(ACTION);
        Media media = mediaService.getMediaBy(103L);
        UpdateMediaResponse response = mediaService.update(103L,request);
        assertThat(response).isNotNull();

         category = mediaService.getMediaBy(103L).getCategory();
        assertThat(category).isEqualTo(ACTION);

    }


}
