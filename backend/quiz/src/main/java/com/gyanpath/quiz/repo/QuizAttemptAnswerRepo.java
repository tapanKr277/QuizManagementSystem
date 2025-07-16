package com.gyanpath.quiz.repo;

import com.gyanpath.quiz.entity.QuizAttemptAnswer;
import com.gyanpath.quiz.util.QuizAttemptAnswerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAttemptAnswerRepo extends JpaRepository<QuizAttemptAnswer, QuizAttemptAnswerId> {
}
