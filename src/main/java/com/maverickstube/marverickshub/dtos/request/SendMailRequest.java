package com.maverickstube.marverickshub.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SendMailRequest {
    private String recipientEmail;
    private String subject;
    private String recipientName;
    private String content;
}
