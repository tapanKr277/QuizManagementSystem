package com.gyanpath.quiz.util;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class QuizAttemptAnswerId {

    @Column(name = "quiz_attempt_id")
    private QuizAttemptId quizAttemptId;
    @Column(name = "question_id")
    private Short questionId;

}
