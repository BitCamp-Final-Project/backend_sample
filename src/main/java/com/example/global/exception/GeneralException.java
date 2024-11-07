package com.example.global.exception;

import com.example.global.code.ErrorCode;
import com.example.global.code.ErrorDTO;
import com.example.global.code.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private ErrorCode code;

    public ErrorDTO getErrorReason(){
        return this.code.getReason();
    }
    public ErrorDTO getErrorReasonHttpStatus(){
        return this.code.getHttpStatusReason();
    }
}
