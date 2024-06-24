package com.maverickstube.marverickshub.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;
    @Test
    public void sendEmailTest() {
        String email  = "becexi6393@cnurbano.com";
        String response = mailService.sendMail(email);

        assertThat(response).isNotNull();
        assertThat(response).contains("Success");
    }
}
