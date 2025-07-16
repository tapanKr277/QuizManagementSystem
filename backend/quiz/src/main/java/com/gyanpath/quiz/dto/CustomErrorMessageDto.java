package com.gyanpath.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorMessageDto {

    private String apiPath;
    private LocalDateTime errorTime;
    private String errorMessage;
    private int statusCode;

}
