package com.gyanpath.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gyanpath.quiz.util.QuizAttemptAnswerId;
import com.gyanpath.quiz.util.QuizAttemptId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttemptAnswerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private QuizAttemptAnswerId quizAttemptId;
    private Short questionId;
    private String answer;

}
