package com.gyanpath.quiz.mapper;

import com.gyanpath.quiz.dto.QuestionDto;
import com.gyanpath.quiz.dto.QuizDto;
import com.gyanpath.quiz.entity.Quiz;


public class QuizMapper {

    public static QuizDto quizToQuizDto(Quiz quiz){
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(quiz.getQuizId());
        quizDto.setTitle(quiz.getTitle());
        quizDto.setCategory(quiz.getCategory());
        quizDto.setQuizTime(quiz.getQuizTime()/60.0);
        quizDto.setTotalMarks(quiz.getTotalMarks());
        quizDto.setTotalQuestion(quiz.getTotalQuestion());
        quizDto.setDifficultyLevel(quiz.getDifficultyLevel());
        return quizDto;
    }

    public static Quiz quizDtoToQuiz(QuizDto quizDto){
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setCategory(quizDto.getCategory());
        Double quizTimeInSeconds = quizDto.getQuizTime()*60;
        quiz.setQuizTime(quizTimeInSeconds);
        quiz.setDifficultyLevel(quizDto.getDifficultyLevel());
        double totalMarks = 0.0;
        for(QuestionDto questionDto: quizDto.getQuestionDtoList()){
            totalMarks+=questionDto.getMarks();
        }
        quiz.setTotalQuestion(quizDto.getQuestionDtoList().size());
        quiz.setTotalMarks(totalMarks);
        return quiz;
    }
}