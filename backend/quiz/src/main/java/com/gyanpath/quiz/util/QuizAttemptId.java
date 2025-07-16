package com.gyanpath.quiz.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class QuizAttemptId {

    @Column(name = "user_id")
    private Short userId;

    @Column(name = "quiz_id")
    private Short quizId;
}
