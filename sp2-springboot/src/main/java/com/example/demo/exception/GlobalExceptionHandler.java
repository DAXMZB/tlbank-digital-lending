package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.common.ErrorResponse;
import com.example.demo.service.MessageService;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        HttpStatus status = switch (ex.getErrorCode()) {
            case MEMBER_NOT_FOUND, PRODUCT_NOT_FOUND, ORDER_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case MEMBER_DUPLICATE_USERNAME, ORDER_CONFLICT -> HttpStatus.CONFLICT;
            case LOGIN_FAILED, ORDER_STATUS_INVALID, PRODUCT_STOCK_NOT_ENOUGH -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.BAD_REQUEST;
        };

        return ResponseEntity.status(status)
                .body(new ErrorResponse(false, ex.getErrorCode().name(), ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(false, ErrorCode.VALIDATION_ERROR.name(), message, LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(false, ErrorCode.SYSTEM_ERROR.name(), ex.getMessage(), LocalDateTime.now()));
    }
}
