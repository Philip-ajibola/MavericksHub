package com.maverickstube.marverickshub.services;

import com.maverickstube.marverickshub.config.MailConfig;
import com.maverickstube.marverickshub.dtos.request.BrevoMailRequest;
import com.maverickstube.marverickshub.dtos.request.Recipient;
import com.maverickstube.marverickshub.dtos.request.SendMailRequest;
import com.maverickstube.marverickshub.dtos.request.Sender;
import com.maverickstube.marverickshub.dtos.response.BrevoMailResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
    private final MailConfig mailConfig;
    @Override
    public String sendMail(SendMailRequest sendMailRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = mailConfig.getMailApiUrl();
        BrevoMailRequest request = new BrevoMailRequest();
        request.setSubject(sendMailRequest.getSubject());
        request.setSender(new Sender());
        request.setRecipients(List.of(new Recipient(sendMailRequest.getRecipientName(),sendMailRequest.getRecipientEmail())));
        request.setContent(sendMailRequest.getContent());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set("api-key",mailConfig.getMailApiKey());
        headers.set("accept",APPLICATION_JSON.toString());
        RequestEntity<?> httpRequest = new RequestEntity<>(request, headers, HttpMethod.POST, URI.create(url));
       ResponseEntity<BrevoMailResponse> response = restTemplate.postForEntity(url,request, BrevoMailResponse.class);
       if(response.getBody() != null && response.getStatusCode() == HttpStatusCode.valueOf(201)){
           return "mail sent successfully";
       }else throw new RuntimeException("Failed to send mail");
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "password";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
