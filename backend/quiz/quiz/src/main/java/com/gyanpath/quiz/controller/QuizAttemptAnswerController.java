package com.gyanpath.quiz.controller;

import com.gyanpath.quiz.service.QuizAttemptAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz/quiz-attempt-answer")
public class QuizAttemptAnswerController {

    @Autowired
    private QuizAttemptAnswerService quizAttemptAnswerService;
}
