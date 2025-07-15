package com.gyanpath.question.exception;

import com.gyanpath.question.dto.CustomErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorMessageDto> globalException(Exception exp,  WebRequest webRequest){
        CustomErrorMessageDto customErrorMessageDto = new CustomErrorMessageDto();
        customErrorMessageDto.setErrorMessage(exp.getMessage());
        customErrorMessageDto.setApiPath(webRequest.getDescription(false));
        customErrorMessageDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        customErrorMessageDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(customErrorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<CustomErrorMessageDto> resourceNotFoundException(ResourceNotFound exp,  WebRequest webRequest){
        CustomErrorMessageDto customErrorMessageDto = new CustomErrorMessageDto();
        customErrorMessageDto.setErrorMessage(exp.getMessage());
        customErrorMessageDto.setApiPath(webRequest.getDescription(false));
        customErrorMessageDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        customErrorMessageDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(customErrorMessageDto, HttpStatus.BAD_REQUEST);
    }
}
