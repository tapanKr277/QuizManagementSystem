package com.gyanpath.quiz.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id", nullable = false)
    private Short quizId;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "category", nullable = false, length = 20)
    private String category;

    @Column(name = "difficulty_level", nullable = false, length = 20)
    private String difficultyLevel;

    @Column(name="quiz_time", nullable = false)
    private Double quizTime;

    @Column(name = "total_marks", nullable = false)
    private double totalMarks;

    @ElementCollection
    @Column(name = "question_id")
    private List<Short> questionIds = new ArrayList<>();

    @Column(name = "total_question")
    private Integer totalQuestion = questionIds.size();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<QuizAttempt> quizAttemptList = new ArrayList<>();

}
