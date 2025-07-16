package com.gyanpath.security.service.client;

import com.gyanpath.security.dto.QuestionDto;
import com.gyanpath.security.exception.ResourceNotFound;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "question")
public interface QuestionServiceClient {

    @GetMapping("/api/question/get-all-question-list")
    public ResponseEntity<List<QuestionDto>> getAllQuestion();

    @PutMapping("/api/question/update-question-by-id")
    public ResponseEntity<QuestionDto> updateQuestionById(@RequestParam(name = "question_id") Short questionId, @RequestBody QuestionDto questionDto) throws ResourceNotFound;

    @PostMapping("/api/question/create-question")
    public List<Short> createQuestions(@RequestBody List<QuestionDto> questionDtoList);

}