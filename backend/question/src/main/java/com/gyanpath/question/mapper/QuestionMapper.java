package com.gyanpath.question.mapper;

import com.gyanpath.question.dto.QuestionDto;
import com.gyanpath.question.entity.Question;

public class QuestionMapper {

    public static QuestionDto questionToQuestionDto(Question question){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setMarks(question.getMarks());
        questionDto.setQuestion(question.getQuestion());
        questionDto.setOption1(question.getOption1());
        questionDto.setOption2(question.getOption2());
        questionDto.setOption3(question.getOption3());
        questionDto.setOption4(question.getOption4());
        return questionDto;
    }

    public static Question questionDtoToQuestion(QuestionDto questionDto){
        Question question = new Question();
        question.setQuestion(questionDto.getQuestion());
        question.setMarks(questionDto.getMarks());
        question.setOption1(questionDto.getOption1());
        question.setOption2(questionDto.getOption2());
        question.setOption3(questionDto.getOption3());
        question.setOption4(questionDto.getOption4());
        question.setAnswer(questionDto.getAnswer());
        return question;
    }
}
