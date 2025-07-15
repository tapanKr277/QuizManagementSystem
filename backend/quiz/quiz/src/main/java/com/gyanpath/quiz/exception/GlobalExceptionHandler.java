package com.gyanpath.quiz.exception;


import com.gyanpath.quiz.dto.CustomErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

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


    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<CustomErrorMessageDto> quizNotFoundException(QuizNotFoundException exp, WebRequest webRequest){
        CustomErrorMessageDto customErrorMessageDto = new CustomErrorMessageDto();
        customErrorMessageDto.setErrorMessage(exp.getMessage());
        customErrorMessageDto.setApiPath(webRequest.getDescription(false));
        customErrorMessageDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        customErrorMessageDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(customErrorMessageDto, HttpStatus.NOT_FOUND);
    }


}
