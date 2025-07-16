package com.gyanpath.quiz.service;

import com.gyanpath.quiz.dto.QuizAttemptDto;
import com.gyanpath.quiz.exception.QuizAlreadyCompletedException;
import com.gyanpath.quiz.exception.QuizNotFoundException;

import java.time.Year;
import java.util.List;
import java.util.Map;

public interface QuizAttemptService {

    QuizAttemptDto createQuizAttempt(Short quizId, Short userId) throws QuizNotFoundException, QuizAlreadyCompletedException;

    QuizAttemptDto updateQuizAttempt(QuizAttemptDto quizAttemptDto) throws QuizNotFoundException;

    QuizAttemptDto submitQuizAttempt(QuizAttemptDto quizAttemptDto) throws QuizNotFoundException;

    List<QuizAttemptDto> getUserQuizAttemptList(Short userId);

    QuizAttemptDto updateWarningCount(Short quizId, Short userId, Integer warningCount) throws QuizNotFoundException;

    List<Map<Integer, Integer>> getNumberOfQuizAttemptMonthWiseInYear(Short userId, Year year);

    Boolean deleteQuizAttempt(Short userId);
}
