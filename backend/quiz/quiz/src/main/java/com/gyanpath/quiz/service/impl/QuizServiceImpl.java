package com.gyanpath.quiz.service.impl;

import com.gyanpath.quiz.dto.QuestionByIdsDto;
import com.gyanpath.quiz.dto.QuestionDto;
import com.gyanpath.quiz.dto.QuizCategoryDto;
import com.gyanpath.quiz.dto.QuizDto;
import com.gyanpath.quiz.entity.Quiz;
import com.gyanpath.quiz.exception.QuizNotFoundException;
import com.gyanpath.quiz.service.client.QuestionServiceClient;
import com.gyanpath.quiz.mapper.QuizMapper;
import com.gyanpath.quiz.repo.QuizRepo;
import com.gyanpath.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionServiceClient questionServiceClient;

    @Override
    public QuizDto getQuizById(Short id) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizRepo.findById(id);
        if(quiz.isEmpty()){
            throw new QuizNotFoundException("Quiz not available");
        }
        QuestionByIdsDto questionByIdsDto = new QuestionByIdsDto();
        questionByIdsDto.setListQuestionIds(quiz.get().getQuestionIds());
        List<QuestionDto> questionDtoList = questionServiceClient.getQuestionsByIds(questionByIdsDto).getBody();
        QuizDto quizDto = QuizMapper.quizToQuizDto(quiz.get());
        quizDto.setQuestionDtoList(questionDtoList);
        return quizDto;
    }

    @Override
    public void createQuiz(QuizDto quizDto) {
        Quiz quiz = QuizMapper.quizDtoToQuiz(quizDto);
        List<QuestionDto> questionDtoList = quizDto.getQuestionDtoList();
        List<Short> questionIds = questionServiceClient.createQuestions(questionDtoList);
        quiz.setQuestionIds(questionIds);

        quizRepo.save(quiz);
    }

    @Override
    public Double calculateQuizScore(Short quizId) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        Double score = 0.0;
        if(quiz.isEmpty()){
            throw new QuizNotFoundException("Quiz not available");
        }
        List<Short> questionIdList = quiz.get().getQuestionIds();
        questionServiceClient.getQuestionByIdsForResult(new QuestionByIdsDto(questionIdList));

        return score;
    }

    @Override
    public List<QuizDto> getAllQuiz() {
        List<Quiz> quizList = quizRepo.findAll();
        QuestionByIdsDto questionByIdsDto = new QuestionByIdsDto();
        List<QuizDto> quizDtoList = new ArrayList<>();
        for(Quiz quiz: quizList){
            questionByIdsDto.setListQuestionIds(quiz.getQuestionIds());
            List<QuestionDto> questionDtoList = questionServiceClient.getQuestionsByIds(questionByIdsDto).getBody();
            QuizDto quizDto = QuizMapper.quizToQuizDto(quiz);
            quizDto.setQuestionDtoList(questionDtoList);
            quizDtoList.add(quizDto);
        }
        return quizDtoList;
    }

    @Override
    public List<QuizCategoryDto> getAllQuizCategory() {
//        List<QuizCategoryDto> quizCategoryDtoList = quizRepo.findAllCategory();
        return List.of();
    }

    @Override
    public List<QuizDto> getQuizByCategory(String category) {
        List<Quiz> quizList = quizRepo.findByCategory(category);
        List<QuizDto> quizDtoList = new ArrayList<>();
        quizList.forEach((quiz)-> quizDtoList.add(QuizMapper.quizToQuizDto(quiz)));
        return quizDtoList;
    }

    @Override
    public List<QuizDto> getQuizByLevel(String level) {
        List<Quiz> quizList = quizRepo.findByDifficultyLevel(level);
        List<QuizDto> quizDtoList = new ArrayList<>();
        quizList.forEach((quiz)-> quizDtoList.add(QuizMapper.quizToQuizDto(quiz)));
        return quizDtoList;
    }

    @Override
    public List<QuizDto> getOnlyQuiz() {
        List<Quiz> quizList = quizRepo.findAll();
        List<QuizDto> quizDtoList = new ArrayList<>();
        quizList.forEach(quiz-> quizDtoList.add(QuizMapper.quizToQuizDto(quiz)));
        return quizDtoList;
    }

    @Override
    public void deleteQuizById(Short quizId) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        if(quiz.isEmpty()){
            throw new QuizNotFoundException("Quiz not found with this id "+ quizId);
        }
        quizRepo.deleteById(quizId);
    }
}
