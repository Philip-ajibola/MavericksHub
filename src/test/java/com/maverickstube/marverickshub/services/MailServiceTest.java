package com.maverickstube.marverickshub.services;

import com.maverickstube.marverickshub.dtos.request.SendMailRequest;
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
        String email  = "nader33686@dcbin.com";
        SendMailRequest mailRequest = new SendMailRequest(email,"Hello","john","<p>Hey John!!, Saying hello from the other side</p>");
        String response = mailService.sendMail(mailRequest);

        assertThat(response).isNotNull();
        assertThat(response).contains("Success");
    }
}
