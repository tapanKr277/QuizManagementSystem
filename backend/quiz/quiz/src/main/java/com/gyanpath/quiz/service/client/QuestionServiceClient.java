package com.gyanpath.quiz.service.client;

import com.gyanpath.quiz.dto.QuestionByIdsDto;
import com.gyanpath.quiz.dto.QuestionDto;
import com.gyanpath.quiz.dto.QuizAttemptAnswerDto;
import com.gyanpath.quiz.mapper.QuestionAnswerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient("question")
public interface QuestionServiceClient {

    @PostMapping("/api/question/create-question")
    public List<Short> createQuestions(@RequestBody List<QuestionDto> questionDtoList);

    @PostMapping("/api/question/get-questions-by-ids-for-result")
    public ResponseEntity<List<QuestionDto>> getQuestionByIdsForResult(@RequestBody QuestionByIdsDto listOfQuestionIds);

    @PostMapping("/api/question/get-questions-by-id-list")
    public ResponseEntity<List<QuestionDto>> getQuestionsByIds(@RequestBody QuestionByIdsDto listOfQuestionIds);

    @PostMapping("/api/question/calculate-score")
    public ResponseEntity<Map<String, Double>> calculateScore(@RequestBody List<QuizAttemptAnswerDto> quizAttemptAnswerDtoList);

}