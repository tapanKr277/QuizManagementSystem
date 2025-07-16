package com.gyanpath.quiz.controller;


import com.gyanpath.quiz.dto.QuizAttemptDto;
import com.gyanpath.quiz.exception.QuizAlreadyCompletedException;
import com.gyanpath.quiz.exception.QuizNotFoundException;
import com.gyanpath.quiz.service.QuizAttemptService;
import com.gyanpath.quiz.service.client.QuestionServiceClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz/quiz-attempt")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService quizAttemptService;

    @Autowired
    private QuestionServiceClient questionServiceClient;


    @GetMapping("/create")
    @Transactional
    public ResponseEntity<?> createQuizAttempt(@RequestParam(name = "quiz_id") Short quizId, @RequestParam(name = "user_id") Short userId) throws QuizNotFoundException, QuizAlreadyCompletedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizAttemptService.createQuizAttempt(quizId, userId));
    }

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<QuizAttemptDto> updateQuizAttempt(@RequestBody QuizAttemptDto quizAttemptDto) throws QuizNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(quizAttemptService.updateQuizAttempt(quizAttemptDto));
    }

    @PostMapping("/submit")
    @Transactional
    public ResponseEntity<QuizAttemptDto> submitQuizAttempt(@RequestBody QuizAttemptDto quizAttemptDto) throws QuizNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(quizAttemptService.submitQuizAttempt(quizAttemptDto));
    }

    @GetMapping("/get-user-quiz-attempt-list")
    public ResponseEntity<List<QuizAttemptDto>> getUserQuizAttemptList(@RequestParam(name = "user_id") Short userId){
        return ResponseEntity.status(HttpStatus.OK).body(quizAttemptService.getUserQuizAttemptList(userId));
    }

    @GetMapping("/get-number-of-quiz-attempt-month-wise")
    public ResponseEntity<List<Map<Integer, Integer>>> getNumberOfQuizAttemptMonthWiseInYear(@RequestParam Short userId, @RequestParam Year year){
        return ResponseEntity.status(HttpStatus.OK).body(quizAttemptService.getNumberOfQuizAttemptMonthWiseInYear(userId, year));
    }

    @GetMapping("/update-warning-count")
    public ResponseEntity<Map<String, Integer>> updateWarningCount(@RequestParam Short quizId,@RequestParam Short userId,@RequestParam Integer warningCount) throws QuizNotFoundException {
        Map<String, Integer> response = new HashMap<>();
        response.put("warningCount",quizAttemptService.updateWarningCount(quizId, userId, warningCount).getWarningCount());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Transactional
    @DeleteMapping("/delete-quiz-attempt")
    public ResponseEntity<?> deleteQuizAttempt(@RequestParam Short userId){
        quizAttemptService.deleteQuizAttempt(userId);
        return ResponseEntity.noContent().build();
    }
}
