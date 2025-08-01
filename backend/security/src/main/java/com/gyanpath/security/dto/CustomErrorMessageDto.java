package com.gyanpath.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomErrorMessageDto {

    private String path;
    private String error;
    private int status;
    private LocalDateTime timestamp;
}
