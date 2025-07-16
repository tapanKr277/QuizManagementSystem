package com.gyanpath.quiz.service;

import com.gyanpath.quiz.dto.QuizCategoryDto;
import com.gyanpath.quiz.dto.QuizDto;
import com.gyanpath.quiz.exception.QuizNotFoundException;

import java.util.List;

public interface QuizService {

    QuizDto getQuizById(Short id) throws QuizNotFoundException;

    void createQuiz(QuizDto quizDto);

    Double calculateQuizScore(Short quizId) throws QuizNotFoundException;

    List<QuizDto> getAllQuiz();

    List<QuizCategoryDto> getAllQuizCategory();

    List<QuizDto> getQuizByCategory(String category);

    List<QuizDto> getQuizByLevel(String level);

    List<QuizDto> getOnlyQuiz();

    void deleteQuizById(Short quizId) throws QuizNotFoundException;
}
