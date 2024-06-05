package com.maverickstube.marverickshub.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maverickstube.marverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.marverickshub.dtos.response.MediaResponse;
import com.maverickstube.marverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.marverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.marverickshub.exception.MediaNotFoundException;
import com.maverickstube.marverickshub.exception.MediaUploadFailedException;
import com.maverickstube.marverickshub.models.Media;
import com.maverickstube.marverickshub.models.User;
import com.maverickstube.marverickshub.repository.MediaRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MavericksHubMediaService implements MediaService {
    private MediaRepository mediaRepository;
    private Cloudinary cloudinary;
    private ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public UploadMediaResponse upload(UploadMediaRequest uploadRequest) {
        User user = userService.getUserById(uploadRequest.getUserId());
        try {
            Map map = ObjectUtils.asMap("resource_type", "auto");
            Map<?, ?> response = cloudinary.uploader().upload(uploadRequest.getMediaFile().getBytes(), map);
            String url = response.get("url").toString();
            Media media = modelMapper.map(uploadRequest, Media.class);
            media.setUrl(url);
            media.setUser(user);
            media = mediaRepository.save(media);
            return modelMapper.map(media, UploadMediaResponse.class);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new MediaUploadFailedException("media upload failed");
        }
    }

    @Override
    public UpdateMediaResponse update(Long id,JsonPatch updateRequest) {
        try {
            Media media = getMediaBy(id);//get target media
            ObjectMapper objectMapper = new ObjectMapper();// convert media to JsonNode(using objectMapper)
            JsonNode jsonMedia = objectMapper.convertValue(media, JsonNode.class);

            jsonMedia = updateRequest.apply(jsonMedia);
            //apply jsonNode to JsonMedia(using objectMapper)
            media = objectMapper.convertValue(jsonMedia, Media.class);
            media = mediaRepository.save(media);
            return modelMapper.map(media, UpdateMediaResponse.class);
        }catch(JsonPatchException exception){
            throw new MediaUploadFailedException(exception.getMessage());
        }

    }
    @Override
    public Media getMediaBy(Long mediaId) {
        return mediaRepository.findById(mediaId).orElseThrow(() -> new MediaNotFoundException("Media not found"));
    }

    @Override
    public List<MediaResponse> getUserMedias(Long userId) {
        List<Media> medias = mediaRepository.findAllMediaFor(userId);
        return medias.stream().map(mediaItem -> modelMapper.map(mediaItem, MediaResponse.class)).collect(Collectors.toList());
    }
}
