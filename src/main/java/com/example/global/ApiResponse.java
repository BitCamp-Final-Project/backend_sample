package com.example.global;

import com.example.global.code.resultCode.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "code", "message", "result"})
public class ApiResponse <T>{
    private final boolean success;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    public static <T> ApiResponse<T> onSuccess(T result, SuccessStatus successStatus){
        return new ApiResponse<>(true, successStatus.getCode(),successStatus.getMessage(), result);
    }
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(false, code, message, data);
    }
}
