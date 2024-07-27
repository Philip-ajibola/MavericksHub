package com.maverickstube.marverickshub.services;

import com.maverickstube.marverickshub.dtos.request.SendMailRequest;

public interface MailService {
    String sendMail(SendMailRequest request);
}
