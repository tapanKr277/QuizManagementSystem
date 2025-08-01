package com.gyanpath.question.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Short questionId;
    private String question;
    private double marks;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String answer;

}
