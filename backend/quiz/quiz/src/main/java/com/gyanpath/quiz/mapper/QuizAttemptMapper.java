package com.gyanpath.quiz.mapper;


import com.gyanpath.quiz.dto.QuizAttemptDto;
import com.gyanpath.quiz.entity.QuizAttempt;
import com.gyanpath.quiz.util.QuizStatus;


public class QuizAttemptMapper {

    public static QuizAttemptDto toDto(QuizAttempt quizAttempt) {
        if (quizAttempt == null) {
            return null;
        }
        QuizAttemptDto quizAttemptDto = new QuizAttemptDto();
        quizAttemptDto.setQuizAttemptId(quizAttempt.getQuizAttemptId());
        quizAttemptDto.setNumberOfQuestionAttempted(quizAttempt.getNumberOfQuestionAttempted());
        quizAttemptDto.setRemarks(quizAttempt.getRemarks());
        quizAttemptDto.setQuizTitle(quizAttempt.getQuiz().getTitle());
        quizAttemptDto.setScore(quizAttempt.getScore());
        quizAttemptDto.setWarningCount(quizAttempt.getWarningCount());
        quizAttemptDto.setTotalQuestion(quizAttempt.getQuiz().getTotalQuestion());
        quizAttemptDto.setNumberOfQuestionAttempted(quizAttempt.getAnswers().size());
        quizAttemptDto.setTimeTaken(quizAttempt.getTimeTaken()/60.0);

        if(quizAttempt.getAnswers().size()!=0){
            quizAttemptDto.setAnswers(QuizAttemptAnswerMapper.toDTOList(quizAttempt.getAnswers()));
        }

        quizAttemptDto.setStatus(quizAttempt.getStatus());
        quizAttemptDto.setCreatedAt(quizAttempt.getCreatedAt());
        quizAttemptDto.setLastUpdate(quizAttempt.getLastUpdate());
        quizAttemptDto.setTotalMarks(quizAttempt.getQuiz().getTotalMarks());
        quizAttemptDto.setQuizTime(quizAttempt.getQuiz().getQuizTime()/60.0);
        return  quizAttemptDto;
    }

    public static QuizAttempt toEntity(QuizAttemptDto quizAttemptDto) {
        if (quizAttemptDto == null) {
            return null;
        }
        QuizAttempt quizAttempt = new QuizAttempt();
        quizAttempt.setQuizAttemptId(quizAttemptDto.getQuizAttemptId());
        quizAttempt.setTimeTaken(0.0);
        quizAttempt.setWarningCount(0);
        quizAttempt.setUserId(quizAttemptDto.getQuizAttemptId().getUserId());
        quizAttempt.setStatus(QuizStatus.PENDING);

        return quizAttempt;
    }
}
