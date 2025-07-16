package com.gyanpath.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    private short quizId;
    private String title;
    private String category;


//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double totalMarks;

    private String difficultyLevel;
    private Double quizTime;
    private List<QuestionDto> questionDtoList = new ArrayList<>();

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer totalQuestion;

}
