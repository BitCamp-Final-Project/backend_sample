package com.example.global.code.resultCode;


import com.example.global.code.ErrorCode;
import com.example.global.code.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements ErrorCode {
    // user
    USER_EXIST_GET_ERROR(HttpStatus.OK,"USERID403", "중복된 id입니다."),


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GLOBAL501","서버 오류")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorDTO getReason() {
        return ErrorDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorDTO getHttpStatusReason() {
        return ErrorDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
