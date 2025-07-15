package com.gyanpath.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private short quizId;
    private String title;
    private String category;
    private String difficultyLevel;
    private Integer quizTime;
    private List<QuestionDto> questionDtoList;

}
