package com.gyanpath.quiz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gyanpath.quiz.util.QuizAttemptAnswerId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "quiz_attempt_answer")
public class QuizAttemptAnswer {

    @EmbeddedId
    @Column(name = "quiz_attempt_answer_id")
    private QuizAttemptAnswerId quizAttemptAnswerId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumns({
            @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id", insertable = false, updatable = false),
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    })
    private QuizAttempt quizAttempt;

    @Column(name = "question_id", insertable=false, updatable=false)
    private Short questionId;

    @Column(name = "answer")
    private String answer;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

}
