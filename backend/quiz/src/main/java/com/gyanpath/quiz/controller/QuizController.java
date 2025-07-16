package com.gyanpath.quiz.controller;

import com.gyanpath.quiz.dto.QuizCategoryDto;
import com.gyanpath.quiz.dto.QuizDto;
import com.gyanpath.quiz.exception.QuizNotFoundException;
import com.gyanpath.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz/")
@Tag(name = "Quiz Controller", description = "Quiz controller for add,remove,update,delete operation")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/check-quiz-by-id")
    public boolean checkQuizById(@RequestParam short quizId) throws  QuizNotFoundException{
        QuizDto quizDto = quizService.getQuizById(quizId);
        return true;
    }


    @GetMapping("/get-quiz-by-id")
    public ResponseEntity<QuizDto> getQuizById(@RequestParam Short id) throws QuizNotFoundException {
        QuizDto quizDtoList = quizService.getQuizById(id);
        return ResponseEntity.ok(quizDtoList);
    }

    @PostMapping("/create-quiz")
    @Transactional
    public ResponseEntity<Map<String, String>> createQuiz(@RequestBody QuizDto quizDto){
        Map<String, String> response = new HashMap<>();
        try{
            quizService.createQuiz(quizDto);
            response.put("success", "Quiz Created successfully");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.put("error", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/calculate-quiz-score/")
    public ResponseEntity<Map<String, Double>> calculateQuizScore(@RequestParam(name="quiz_id") Short quizId) throws QuizNotFoundException {
        Map<String, Double> response = new HashMap<>();
        response.put("score", quizService.calculateQuizScore(quizId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-quiz")
    public ResponseEntity<List<QuizDto>> getAllQuiz(){
        List<QuizDto> quizDtoList = quizService.getAllQuiz();
        return ResponseEntity.ok(quizDtoList);
    }


    @GetMapping("/get-only-quiz")
    public ResponseEntity<List<QuizDto>> getOnlyQuiz(){
        List<QuizDto> quizDtoList = quizService.getOnlyQuiz();
        return ResponseEntity.ok(quizDtoList);
    }

    @GetMapping("/get-all-quiz-category")
    public ResponseEntity<List<QuizCategoryDto>> getAllQuizCategory(){
        return ResponseEntity.ok(quizService.getAllQuizCategory());
    }


    @GetMapping("/get-quiz-by-category")
    public ResponseEntity<List<QuizDto>> getQuizByCategory(@RequestParam String category){
        return ResponseEntity.ok(quizService.getQuizByCategory(category));
    }

    @GetMapping("/get-quiz-by-level")
    public ResponseEntity<List<QuizDto>> getQuizByLevel(@RequestParam String level){
        return ResponseEntity.ok(quizService.getQuizByLevel(level));
    }

    @DeleteMapping("/delete-quiz")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteQuiz(Short quizId) throws QuizNotFoundException {
        quizService.deleteQuizById(quizId);
        Map<String, String> response = new HashMap<>();
        response.put("success", "deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
