package com.gyanpath.quiz.mapper;


import com.gyanpath.quiz.dto.QuizAttemptAnswerDto;
import com.gyanpath.quiz.entity.QuizAttempt;
import com.gyanpath.quiz.entity.QuizAttemptAnswer;
import com.gyanpath.quiz.util.QuizAttemptAnswerId;
import com.gyanpath.quiz.util.QuizAttemptId;

import java.util.List;
import java.util.stream.Collectors;

public class QuizAttemptAnswerMapper {

    public static QuizAttemptAnswerDto toDTO(QuizAttemptAnswer quizAttemptAnswer) {
        if (quizAttemptAnswer == null) {
            return null;
        }
        return new QuizAttemptAnswerDto(
                quizAttemptAnswer.getQuizAttemptAnswerId(),
                quizAttemptAnswer.getQuestionId(),
                quizAttemptAnswer.getAnswer()
        );
    }

    // Convert a list of QuizAttemptAnswer entities to a list of DTOs (Static method)
    public static List<QuizAttemptAnswerDto> toDTOList(List<QuizAttemptAnswer> quizAttemptAnswers) {
        if (quizAttemptAnswers == null) {
            return null;
        }
        return quizAttemptAnswers.stream()
                .map(QuizAttemptAnswerMapper::toDTO)
                .collect(Collectors.toList());
    }


    public static QuizAttemptAnswer toEntity(QuizAttemptAnswerDto dto, QuizAttemptId quizAttemptId) {
        if (dto == null) {
            return null;
        }

        QuizAttemptAnswer quizAttemptAnswer = new QuizAttemptAnswer();
        quizAttemptAnswer.setQuizAttemptAnswerId(new QuizAttemptAnswerId(quizAttemptId, dto.getQuestionId()));
        quizAttemptAnswer.setAnswer(dto.getAnswer());
        quizAttemptAnswer.setQuestionId(dto.getQuestionId());


        return quizAttemptAnswer;
    }
}
