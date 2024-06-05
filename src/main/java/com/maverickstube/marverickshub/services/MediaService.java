package com.maverickstube.marverickshub.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.maverickstube.marverickshub.dtos.request.UpdateMediaRequest;
import com.maverickstube.marverickshub.dtos.request.UploadMediaRequest;
import com.maverickstube.marverickshub.dtos.response.MediaResponse;
import com.maverickstube.marverickshub.dtos.response.UpdateMediaResponse;
import com.maverickstube.marverickshub.dtos.response.UploadMediaResponse;
import com.maverickstube.marverickshub.models.Media;

import java.util.List;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest uploadRequest);
    UpdateMediaResponse update(Long id,JsonPatch updateRequest);
    Media getMediaBy(Long id);

    List<MediaResponse> getUserMedias(Long userId);
}
