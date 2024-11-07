package com.example.global.code.resultCode;

import com.example.global.code.SuccessCode;
import com.example.global.code.SuccessDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor  // 열거형 필드가 3개(httpstatus, code, message)가 있고, @AllArgsConstructor를 했기 때문에 이게 열거형을 정의할 때도 적용이 된다.
// Naming Format : {행위}_{목적어}_{성공여부}
public enum SuccessStatus implements SuccessCode {
    // Common
    _OK(HttpStatus.OK,"COMMON200", "성공입니다"),

    // user
    USER_EXIST_GET_SUCCESS(HttpStatus.OK,"USER200", "사용 가능한 id입니다."),

    BOARD_GET_SUCCESS(HttpStatus.OK,"BOARD200", "게시판 조회 성공"),
    BOARDLIST_GET_SUCCESS(HttpStatus.OK,"BOARD201", "전체 게시판 조회 성공")
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public SuccessDTO getReason() {
        return SuccessDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public SuccessDTO getHttpStatusReason() {
        return SuccessDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
