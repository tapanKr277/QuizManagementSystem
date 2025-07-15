package com.gyanpath.quiz.service.impl;

import com.gyanpath.quiz.repo.QuizAttemptAnswerRepo;
import com.gyanpath.quiz.service.QuizAttemptAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizAttemptAnswerServiceImpl implements QuizAttemptAnswerService {

    @Autowired
    private QuizAttemptAnswerRepo quizAttemptAnswerRepo;
}
