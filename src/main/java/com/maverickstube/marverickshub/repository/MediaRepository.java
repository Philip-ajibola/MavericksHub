package com.maverickstube.marverickshub.repository;

import com.maverickstube.marverickshub.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media,Long> {
    @Query("SELECT m FROM Media m where m.user.id=:userId")
    List<Media> findAllMediaFor(Long userId);
}
