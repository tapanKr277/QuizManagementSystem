package com.gyanpath.security.exception;

import com.gyanpath.security.dto.CustomErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorMessageDto> globalException(Exception exp, WebRequest webRequest){
        CustomErrorMessageDto customErrorMessageDto = new CustomErrorMessageDto();
        customErrorMessageDto.setError(exp.getMessage());
        customErrorMessageDto.setPath(webRequest.getDescription(false));
        customErrorMessageDto.setStatus(HttpStatus.BAD_REQUEST.value());
        customErrorMessageDto.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(customErrorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
