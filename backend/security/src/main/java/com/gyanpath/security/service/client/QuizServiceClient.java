package com.gyanpath.security.service.client;


import com.gyanpath.security.dto.QuizAttemptDto;
import com.gyanpath.security.dto.QuizDto;
import com.gyanpath.security.exception.QuizNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.Map;

@FeignClient("QUIZ")
public interface QuizServiceClient {

    @PostMapping("/api/quiz/create-quiz")
    @Transactional
    public ResponseEntity<Map<String, String>> createQuiz(@RequestBody QuizDto quizDto);

    @DeleteMapping("/api/quiz/delete-quiz")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteQuiz(Short quizId) throws QuizNotFoundException;

    @GetMapping("/api/quiz/get-all-quiz")
    public ResponseEntity<List<QuizDto>> getAllQuiz();

    @GetMapping("/api/quiz/quiz-attempt/create")
    @Transactional
    public ResponseEntity<QuizAttemptDto> createQuizAttempt(@RequestParam(name = "quiz_id") Short quizId, @RequestParam(name = "user_id") Short userId) throws QuizNotFoundException;

    @PostMapping("/api/quiz/quiz-attempt/update")
    @Transactional
    public ResponseEntity<QuizAttemptDto> updateQuizAttempt(@RequestBody QuizAttemptDto quizAttemptDto) throws QuizNotFoundException;


    @PostMapping("/api/quiz/quiz-attempt/submit")
    @Transactional
    public ResponseEntity<QuizAttemptDto> submitQuizAttempt(@RequestBody QuizAttemptDto quizAttemptDto) throws QuizNotFoundException;

    @GetMapping("/api/quiz/quiz-attempt/get-user-quiz-attempt-list")
    public ResponseEntity<List<QuizAttemptDto>> getUserQuizAttemptList(@RequestParam(name = "user_id") Short userId);

    @GetMapping("/api/quiz/get-quiz-by-id")
    public ResponseEntity<QuizDto> getQuizById(@RequestParam Short id) throws QuizNotFoundException;


    @GetMapping("/api/quiz/quiz-attempt/get-number-of-quiz-attempt-month-wise")
    public ResponseEntity<List<Map<Integer, Integer>>> getNumberOfQuizAttemptMonthWiseInYear(@RequestParam Short userId, @RequestParam Year year);


    @GetMapping("/api/quiz/quiz-attempt/update-warning-count")
    public ResponseEntity<Map<String, Integer>> updateWarningCount(@RequestParam Short quizId,@RequestParam Short userId,@RequestParam Integer warningCount) throws QuizNotFoundException;

    @DeleteMapping("/api/quiz/quiz-attempt/delete-quiz-attempt")
    public ResponseEntity<?> deleteQuizAttempt(@RequestParam Short userId);

}