package com.gyanpath.question.controller;

import com.gyanpath.question.dto.QuestionByIdsDto;
import com.gyanpath.question.dto.QuestionDto;
import com.gyanpath.question.dto.QuizAttemptAnswerDto;
import com.gyanpath.question.exception.ResourceNotFound;
import com.gyanpath.question.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
@Tag(name = "Question Controller", description = "Question controller for add,remove,update,delete operation")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/get-all-question-list")
    public ResponseEntity<List<QuestionDto>> getAllQuestion(){
        List<QuestionDto> questionDtoList = questionService.getAllQuestions();
        return ResponseEntity.ok(questionDtoList);
    }


    @PostMapping("/get-questions-by-ids-for-result")
    public ResponseEntity<List<QuestionDto>> getQuestionByIdsForResult(@RequestBody QuestionByIdsDto listOfQuestionIds){
        return ResponseEntity.ok(questionService.getQuestionsByIds(listOfQuestionIds));
    }


    @PostMapping("/get-questions-by-id-list")
    public ResponseEntity<List<QuestionDto>> getQuestionsByIds(@RequestBody QuestionByIdsDto listOfQuestionIds){
        return ResponseEntity.ok(questionService.getQuestionsByIds(listOfQuestionIds));
    }

    @PutMapping("/update-question-by-id")
    public ResponseEntity<QuestionDto> updateQuestionById(@RequestParam(name = "question_id") Short questionId, @RequestBody QuestionDto questionDto) throws ResourceNotFound {
        return ResponseEntity.ok(questionService.updateQuestionById(questionId, questionDto));
    }

    @PostMapping("/create-question")
    public List<Short> createQuestions(@RequestBody List<QuestionDto> questionDtoList){
        System.out.println(questionDtoList);
        Map<String, List<Short>> response = new HashMap<>();
        try{
//            response.put("success", questionService.createQuestion(questionDtoList));
            return questionService.createQuestion(questionDtoList);
        }catch (Exception exp){
//            response.put("error", new ArrayList<>());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ArrayList<>();
        }
    }

    @PostMapping("/calculate-score")
    public ResponseEntity<Map<String, Double>> calculateScore(@RequestBody List<QuizAttemptAnswerDto> quizAttemptAnswerDtoList){
        Map<String, Double> response = new HashMap<>();
        Double score = questionService.calculateScore(quizAttemptAnswerDtoList);
        response.put("score", score);
        return ResponseEntity.ok(response);
    }



}
