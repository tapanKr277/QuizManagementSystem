package com.gyanpath.quiz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gyanpath.quiz.util.QuizAttemptId;
import com.gyanpath.quiz.util.QuizStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz_attempt")
@Entity
public class QuizAttempt {

    @EmbeddedId
    @Column(name = "quiz_attempt_id")
    private QuizAttemptId quizAttemptId;

    @Column(name = "user_id", nullable = false, insertable=false, updatable=false)
    private Short userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false, referencedColumnName = "quiz_id", insertable=false, updatable=false)
    @JsonBackReference
    private Quiz quiz;

    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<QuizAttemptAnswer> answers = new ArrayList<>();


    @Column(name = "warning_count")
    private Integer warningCount = 0;

    @Column(name = "score")
    private Double score = 0.0;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private QuizStatus status = QuizStatus.PENDING;

    @Column(name = "time_taken")
    private Double timeTaken = 0.0;

    @Column(name = "number_of_question_attempted")
    private Integer numberOfQuestionAttempted;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

}
