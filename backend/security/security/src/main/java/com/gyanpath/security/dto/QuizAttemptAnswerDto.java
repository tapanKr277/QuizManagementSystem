package com.gyanpath.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gyanpath.security.util.QuizAttemptAnswerId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttemptAnswerDto {

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private QuizAttemptAnswerId quizAttemptId;
    private Short questionId;
    private String answer;

}
