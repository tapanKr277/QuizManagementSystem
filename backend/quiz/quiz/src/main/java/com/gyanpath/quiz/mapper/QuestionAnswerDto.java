package com.gyanpath.quiz.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDto {
    Map<Short, String> questionAnswerMap = new HashMap<>();
}
