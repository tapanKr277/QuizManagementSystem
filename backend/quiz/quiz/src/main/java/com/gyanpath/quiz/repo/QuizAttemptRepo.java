package com.gyanpath.quiz.repo;

import com.gyanpath.quiz.entity.QuizAttempt;
import com.gyanpath.quiz.util.QuizAttemptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface QuizAttemptRepo extends JpaRepository<QuizAttempt, QuizAttemptId> {

    List<QuizAttempt> findByUserId(Short userId);


    @Query("SELECT FUNCTION('MONTH', q.createdAt) AS month, COUNT(q) AS attemptsCount " +
            "FROM QuizAttempt q " +
            "WHERE q.userId = :userId " +
            "AND FUNCTION('YEAR', q.createdAt) = :year " +
            "GROUP BY FUNCTION('MONTH', q.createdAt) " +
            "ORDER BY month")
    List<Object[]> getNumberOfQuizAttemptMonthWiseInYear(@Param("userId") Short userId, @Param("year") Year year);


}
