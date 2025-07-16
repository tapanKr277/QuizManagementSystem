package com.gyanpath.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {

    private String firstName;
    private String lastName;
    private Short userId;
    private Short studentId;
    private List<Short> quizIds;
    private Short numberOfQuizAttempted;
    private double averageScore;
    private Date createdAt;
    private Date lastUpdate;
}
