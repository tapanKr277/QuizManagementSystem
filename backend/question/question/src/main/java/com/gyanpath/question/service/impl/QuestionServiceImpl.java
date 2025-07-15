package com.gyanpath.question.service.impl;

import com.gyanpath.question.dto.QuestionAnswerDto;
import com.gyanpath.question.dto.QuestionByIdsDto;
import com.gyanpath.question.dto.QuestionDto;
import com.gyanpath.question.dto.QuizAttemptAnswerDto;
import com.gyanpath.question.entity.Question;
import com.gyanpath.question.exception.ResourceNotFound;
import com.gyanpath.question.mapper.QuestionMapper;
import com.gyanpath.question.repositry.QuestionRepo;
import com.gyanpath.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepo.findAll();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        questions.forEach(question->questionDtoList.add(QuestionMapper.questionToQuestionDto(question)));
        return questionDtoList;
    }

    @Override
    public List<Short> createQuestion(List<QuestionDto> questionDtoList) {
        List<Question> questionList = new ArrayList<>();
        questionDtoList.forEach(questionDto -> questionList.add(QuestionMapper.questionDtoToQuestion(questionDto)));
        questionList.forEach(question -> questionRepo.save(question));
        List<Short> questionIds = new ArrayList<>();
        questionList.forEach(question -> questionIds.add(question.getQuestionId()));
        return questionIds;
    }

    @Override
    public List<QuestionDto> getQuestionsByIds(QuestionByIdsDto listOfQuestionIds) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> questions = questionRepo.findAllById(listOfQuestionIds.getListQuestionIds());
        questions.forEach(question -> questionDtoList.add(QuestionMapper.questionToQuestionDto(question)));
        return  questionDtoList;
    }

    @Override
    public QuestionDto updateQuestionById(Short questionId, QuestionDto questionDto) throws ResourceNotFound {
        Question question = questionRepo.findById(questionId).orElseThrow(() -> new ResourceNotFound("Question with this id "+ questionId+" not found."));
        question.setQuestion(questionDto.getQuestion());
        question.setAnswer(questionDto.getAnswer());
        question.setOption1(questionDto.getOption1());
        question.setOption2(questionDto.getOption2());
        question.setOption3(questionDto.getOption3());
        question.setOption4(questionDto.getOption4());
        return QuestionMapper.questionToQuestionDto(questionRepo.save(question));
    }

    @Override
    public double calculateScore(List<QuizAttemptAnswerDto> quizAttemptAnswerDtoList) {
        double score = 0.0;
        for(QuizAttemptAnswerDto quizAttemptAnswerDto: quizAttemptAnswerDtoList){
            Optional<Question> question = questionRepo.findById(quizAttemptAnswerDto.getQuestionId());
            if(question.isPresent()){
                if(question.get().getAnswer().equals(quizAttemptAnswerDto.getAnswer())){
                    score+=question.get().getMarks();
                }
            }
        }
        return score;
    }
}
