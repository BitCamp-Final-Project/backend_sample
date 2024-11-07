package com.example.global.exception;

import com.example.global.ApiResponse;
import com.example.global.code.ErrorDTO;
import com.example.global.code.resultCode.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class}) // 모든 RestController가 붙은 클래스에 대해 전역적인 예외처리를 진행한다.
public class ExceptionAdvice extends ResponseEntityExceptionHandler {  // ResponseEntityExceptionHandler : Spring에서 기본적으로 제공하는 표준 예외 처리 메서드가 담긴
    @ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) { // ConstraintViolationException이 발생하면 validation 메서드 호출
        String errorMessage = e.getConstraintViolations().stream() // ConstraintViolationException이 발생한 경우, 각 제약 위반의 메시지를 스트림으로 변환한 뒤
                .map(constraintViolation -> constraintViolation.getMessage())  // errorMessage로 저장
                .findFirst() // 가장 첫 번째 메시지를 추출해
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생")); // 예외 메시지가 없는 경우에는 RuntimeException을 던짐
        // handleExceptionInternalConstraint 메서드를 호출하여, 예외 응답을 생성해 반환
        return handleExceptionInternalConstraint(e, ErrorStatus.valueOf(errorMessage), HttpHeaders.EMPTY, request);
    }

    // ApiResponse 객체를 생성하고, 예외 응답을 반환
    private ResponseEntity<Object> handleExceptionInternalConstraint(ConstraintViolationException e, ErrorStatus errorStatus, HttpHeaders headers, WebRequest request) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorStatus.getCode(), errorStatus.getMessage(), null); // body에 errorStatus에서 받은 내용을 onFailure에 담음
        return super.handleExceptionInternal( // ResponseEntityExceptionHandler에 있는 handleExceptionInternal 메서드에 호출 -> spring에서 발생하는 예외에 대한 표준 응답을 생성해주는 역할을 해
                e,
                body,
                headers, // HTTP 헤더를 설정
                errorStatus.getHttpStatus(), // HTTP 상태 코드를 지정
                request  // WebRequest request : 웹 요청 정보를 담고 있는 객체로, 예외가 발생한 요청에 대한 정보가 담김
        );
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternalFalse(e, ErrorStatus.INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, ErrorStatus.INTERNAL_SERVER_ERROR.getHttpStatus(), request, e.getMessage());
    }

    private ResponseEntity<Object>handleExceptionInternalFalse(Exception e, ErrorStatus errorStatus, HttpHeaders headers, HttpStatus status,  WebRequest request, String errorMessage)
    {
        ApiResponse<Object>body  = ApiResponse.onFailure(errorStatus.getCode(), errorStatus.getMessage(), errorMessage);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
    }

    @ExceptionHandler
    public ResponseEntity<Object>onThrowException(GeneralException e, HttpServletRequest request) {
        ErrorDTO errorReasonHttpStatus = e.getErrorReasonHttpStatus();
        return handleExceptionRuntime(e, errorReasonHttpStatus, null, request);
    }

    private ResponseEntity<Object> handleExceptionRuntime(Exception e, ErrorDTO reason, HttpHeaders headers, HttpServletRequest request) {
        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);

        WebRequest webRequest = new ServletWebRequest(request);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                reason.getHttpStatus(),
                webRequest
        );
    }

}