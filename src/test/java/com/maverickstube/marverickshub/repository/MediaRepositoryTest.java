package com.maverickstube.marverickshub.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql({"/db/data.sql"})
class MediaRepositoryTest {
    @Autowired
    private MediaRepository mediaRepository;

    @Test
    public void findAllMediaFor(){
        assertThat(mediaRepository.findAllMediaFor(201L)).hasSize(2);
    }
}