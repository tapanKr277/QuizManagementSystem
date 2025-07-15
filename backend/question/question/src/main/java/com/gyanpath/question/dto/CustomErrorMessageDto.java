package com.gyanpath.question.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomErrorMessageDto {

    private String apiPath;
    private LocalDateTime errorTime;
    private String errorMessage;
    private int statusCode;
}
