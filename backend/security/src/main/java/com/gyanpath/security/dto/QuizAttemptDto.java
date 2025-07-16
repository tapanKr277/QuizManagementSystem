package com.gyanpath.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gyanpath.security.util.QuizAttemptId;
import com.gyanpath.security.util.QuizStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizAttemptDto {

    private QuizAttemptId quizAttemptId;

    private List<QuizAttemptAnswerDto> answers = new ArrayList<>();

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String quizTitle;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double quizTime;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalMarks;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private QuizStatus status;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer warningCount = 0;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double score = 0.0;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String remarks;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double timeTaken;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer totalQuestion;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer numberOfQuestionAttempted;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastUpdate;

}
