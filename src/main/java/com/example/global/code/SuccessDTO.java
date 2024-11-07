package com.example.global.code;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class SuccessDTO {
    private HttpStatus httpStatus; // http 상태 코드
    private final boolean isSuccess; // 성공 여부
    private final String code; // 응답 코드
    private final String message; // 메세지
}
