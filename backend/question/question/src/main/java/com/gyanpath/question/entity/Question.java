package com.gyanpath.question.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Short questionId;

    @Column(name = "question", nullable = false, length = 255)
    private String question;

    @Column(name = "marks", nullable = true)
    private double marks = 1.0;

    @Column(name = "option_1", nullable = false)
    private String option1;

    @Column(name = "option_2", nullable = false)
    private String option2;

    @Column(name = "option_3", nullable = false)
    private String option3;

    @Column(name = "option_4", nullable = false)
    private String option4;

    @Column(name = "answer", nullable = false)
    private String answer;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}
