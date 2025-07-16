package com.gyanpath.quiz.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDtoForExam {

    private Short questionId;
    private String question;
    private List<String> options;
}
