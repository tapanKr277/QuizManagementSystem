package com.gyanpath.question.service;

import com.gyanpath.question.dto.QuestionAnswerDto;
import com.gyanpath.question.dto.QuestionByIdsDto;
import com.gyanpath.question.dto.QuestionDto;
import com.gyanpath.question.dto.QuizAttemptAnswerDto;
import com.gyanpath.question.exception.ResourceNotFound;

import java.util.List;

public interface QuestionService {

    List<QuestionDto> getAllQuestions();

    List<Short> createQuestion(List<QuestionDto> questionDtoList);

    List<QuestionDto> getQuestionsByIds(QuestionByIdsDto listOfQuestionIds);

    QuestionDto updateQuestionById(Short questionId, QuestionDto questionDto) throws ResourceNotFound;

    double calculateScore(List<QuizAttemptAnswerDto> quizAttemptAnswerDtoList);
}