package com.maverickstube.marverickshub.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse <T>{
    private T data;
    private int statusCode;
    private boolean status;
}
