package com.gyanpath.quiz.repo;

import com.gyanpath.quiz.dto.QuizCategoryDto;
import com.gyanpath.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Short> {

//    @Query("SELECT new com.gyanpath.quiz.dto.QuizCategoryDto(q.quizId, q.category) from Quiz q")
//    List<QuizCategoryDto> findAllCategory();

    List<Quiz> findByCategory(String category);

    List<Quiz> findByDifficultyLevel(String level);

}
