package com.example.global.code;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorDTO {
    private HttpStatus httpStatus;
    private final boolean isSuccess;
    private final String code;
    private final String message;
}
