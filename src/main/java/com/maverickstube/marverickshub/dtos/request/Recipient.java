package com.maverickstube.marverickshub.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Recipient {
    private String name;
    private String email;
}
