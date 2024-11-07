package com.example.domain.user.controller;

import com.example.domain.user.service.UserService;
import com.example.global.ApiResponse;
import com.example.global.code.resultCode.ErrorStatus;
import com.example.global.code.resultCode.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("getExistId")
    @ResponseBody
    public ApiResponse getExistId(@RequestParam("id")String id) {
        try{
            boolean exist =  userService.getExistId(id);
            if(!exist){
                return ApiResponse.onSuccess(true, SuccessStatus.USER_EXIST_GET_SUCCESS);
            }else{
                return ApiResponse.onFailure(ErrorStatus.USER_EXIST_GET_ERROR.getCode(),
                        ErrorStatus.USER_EXIST_GET_ERROR.getMessage(), ErrorStatus.USER_EXIST_GET_ERROR);
            }
        }catch (Exception e){
            return ApiResponse.onFailure(ErrorStatus.INTERNAL_SERVER_ERROR.getCode(), ErrorStatus.INTERNAL_SERVER_ERROR.getMessage(),
                    ErrorStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
