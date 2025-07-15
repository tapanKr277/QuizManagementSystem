package com.gyanpath.quiz.service.impl;

import com.gyanpath.quiz.dto.QuizAttemptAnswerDto;
import com.gyanpath.quiz.dto.QuizAttemptDto;
import com.gyanpath.quiz.entity.Quiz;
import com.gyanpath.quiz.entity.QuizAttempt;
import com.gyanpath.quiz.entity.QuizAttemptAnswer;
import com.gyanpath.quiz.exception.QuizAlreadyCompletedException;
import com.gyanpath.quiz.exception.QuizNotFoundException;
import com.gyanpath.quiz.mapper.QuizAttemptAnswerMapper;
import com.gyanpath.quiz.mapper.QuizAttemptMapper;
import com.gyanpath.quiz.repo.QuizAttemptAnswerRepo;
import com.gyanpath.quiz.repo.QuizAttemptRepo;
import com.gyanpath.quiz.repo.QuizRepo;
import com.gyanpath.quiz.service.QuizAttemptService;
import com.gyanpath.quiz.service.client.QuestionServiceClient;
import com.gyanpath.quiz.util.QuizAttemptId;
import com.gyanpath.quiz.util.QuizStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

@Service
@Slf4j
public class QuizAttemptServiceImpl implements QuizAttemptService {


    @Autowired
    private QuizAttemptRepo quizAttemptRepo;

    @Autowired
    private QuizAttemptAnswerRepo quizAttemptAnswerRepo;

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionServiceClient questionServiceClient;

    @Override
    @Transactional
    public QuizAttemptDto createQuizAttempt(Short quizId, Short userId) throws QuizNotFoundException, QuizAlreadyCompletedException {
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException("Quiz not found");
        }

        QuizAttemptId quizAttemptId = new QuizAttemptId(userId, quizId);
        Optional<QuizAttempt> quizAttempt = quizAttemptRepo.findById(quizAttemptId);

        if (quizAttempt.isPresent()) {
            // Existing quiz attempt
            QuizAttempt existingQuizAttempt = quizAttempt.get();

            Double timeTaken = existingQuizAttempt.getTimeTaken() ;

            // If the quiz is still in progress and time taken is less than quiz time, just update timeTaken
            if (existingQuizAttempt.getStatus() == QuizStatus.PENDING && timeTaken < quiz.get().getQuizTime()) {
                existingQuizAttempt.setLastUpdate(LocalDateTime.now());
                existingQuizAttempt.setTimeTaken(timeTaken); // Update timeTaken
                return QuizAttemptMapper.toDto(quizAttemptRepo.save(existingQuizAttempt));
            }

            // If quiz time has exceeded, mark as COMPLETED and calculate the score
            if (timeTaken >= quiz.get().getQuizTime()) {
                existingQuizAttempt.setStatus(QuizStatus.COMPLETED);

                List<QuizAttemptAnswer> quizAttemptAnswers = existingQuizAttempt.getAnswers();
                List<QuizAttemptAnswerDto> quizAttemptAnswerDtoList = new ArrayList<>();
                quizAttemptAnswers.forEach(answer -> quizAttemptAnswerDtoList.add(QuizAttemptAnswerMapper.toDTO(answer)));

                try {
                    Map<String, Double> response = questionServiceClient.calculateScore(quizAttemptAnswerDtoList).getBody();
                    existingQuizAttempt.setScore(response.get("score"));
                } catch (Exception e) {
                    log.error(e.getMessage() + " Error occurred while calculating score.");
                    existingQuizAttempt.setScore(0.0); // Default score in case of an error
                }

                existingQuizAttempt.setTimeTaken(timeTaken); // Update timeTaken once completed
                quizAttemptRepo.save(existingQuizAttempt);
                throw new QuizAlreadyCompletedException("Quiz already completed");  // Ensure we throw after saving
            }

            throw new QuizAlreadyCompletedException("Quiz already Completed");
        }

        // Create a new quiz attempt if no previous attempt exists
        QuizAttempt createQuizAttempt = new QuizAttempt();
        createQuizAttempt.setQuizAttemptId(quizAttemptId);
        createQuizAttempt.setQuiz(quiz.get());
        createQuizAttempt.setCreatedAt(LocalDateTime.now()); // Initialize createdAt
        createQuizAttempt.setTimeTaken(0.0); // Initialize timeTaken to 0
        createQuizAttempt.setLastUpdate(LocalDateTime.now());
        return QuizAttemptMapper.toDto(quizAttemptRepo.save(createQuizAttempt));
    }

    @Override
    public QuizAttemptDto updateQuizAttempt(QuizAttemptDto quizAttemptDto) throws QuizNotFoundException {
        Optional<QuizAttempt> quizAttempt = quizAttemptRepo.findById(quizAttemptDto.getQuizAttemptId());
        if (quizAttempt.isEmpty()) {
            throw new RuntimeException("Attempt not found");
        }
        Optional<Quiz> quiz = quizRepo.findById(quizAttemptDto.getQuizAttemptId().getQuizId());
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException("Quiz not found");
        }

        List<QuizAttemptAnswerDto> quizAttemptAnswerDtoList = quizAttemptDto.getAnswers();
        List<QuizAttemptAnswer> quizAttemptAnswers = new ArrayList<>();

        for (QuizAttemptAnswerDto quizAttemptAnswerDto : quizAttemptAnswerDtoList) {
            QuizAttemptAnswer quizAttemptAnswer = QuizAttemptAnswerMapper.toEntity(quizAttemptAnswerDto, quizAttempt.get().getQuizAttemptId());
            quizAttemptAnswer.setQuizAttempt(quizAttempt.get());
            quizAttemptAnswers.add(quizAttemptAnswerRepo.save(quizAttemptAnswer));
        }

        quizAttempt.get().setNumberOfQuestionAttempted(quizAttemptAnswers.size());

        Double timeTaken = quizAttempt.get().getTimeTaken();

        if (quizAttempt.get().getStatus().equals(QuizStatus.PENDING)) {
            timeTaken += calculateTimeTaken(quizAttempt.get().getLastUpdate(), LocalDateTime.now());
            quizAttempt.get().setTimeTaken(timeTaken); // Update the time taken
            quizAttempt.get().setAnswers(quizAttemptAnswers);

            return QuizAttemptMapper.toDto(quizAttemptRepo.save(quizAttempt.get()));
        }

        throw new RuntimeException("Quiz is Completed");
    }

    @Override
    public QuizAttemptDto submitQuizAttempt(QuizAttemptDto quizAttemptDto) throws QuizNotFoundException {
        Optional<QuizAttempt> quizAttempt = quizAttemptRepo.findById(quizAttemptDto.getQuizAttemptId());
        if (quizAttempt.isEmpty()) {
            throw new RuntimeException("Attempt not found");
        }
        Optional<Quiz> quiz = quizRepo.findById(quizAttemptDto.getQuizAttemptId().getQuizId());
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException("Quiz not found");
        }

        List<QuizAttemptAnswerDto> quizAttemptAnswerDtoList = quizAttemptDto.getAnswers();
        try {
            Map<String, Double> response = questionServiceClient.calculateScore(quizAttemptAnswerDtoList).getBody();
            quizAttempt.get().setScore(response.get("score"));
        } catch (Exception e) {
            log.error(e.getMessage());
            quizAttempt.get().setScore(0.0); // Default score in case of an error
        }

        List<QuizAttemptAnswer> quizAttemptAnswers = new ArrayList<>();

        for (QuizAttemptAnswerDto quizAttemptAnswerDto : quizAttemptAnswerDtoList) {
            QuizAttemptAnswer quizAttemptAnswer = QuizAttemptAnswerMapper.toEntity(quizAttemptAnswerDto, quizAttempt.get().getQuizAttemptId());
            quizAttemptAnswer.setQuizAttempt(quizAttempt.get());
            quizAttemptAnswers.add(quizAttemptAnswerRepo.save(quizAttemptAnswer));
        }

        quizAttempt.get().setNumberOfQuestionAttempted(quizAttemptAnswers.size());
        quizAttempt.get().setStatus(QuizStatus.COMPLETED);

        Double timeTaken = quizAttempt.get().getTimeTaken();
        timeTaken += calculateTimeTaken(quizAttempt.get().getLastUpdate(), LocalDateTime.now());
        quizAttempt.get().setTimeTaken(timeTaken); // Update total time taken

        return QuizAttemptMapper.toDto(quizAttemptRepo.save(quizAttempt.get()));
    }


    @Override
    public List<QuizAttemptDto> getUserQuizAttemptList(Short userId) {
        List<QuizAttempt> quizAttemptList = quizAttemptRepo.findByUserId(userId);
        List<QuizAttemptDto> quizAttemptDtoList = new ArrayList<>();
        for(QuizAttempt quizAttempt: quizAttemptList){
            int numberOfQuestionAttempted = quizAttempt.getAnswers().size();
            quizAttempt.setAnswers(new ArrayList<>());
            QuizAttemptDto quizAttemptDto = QuizAttemptMapper.toDto(quizAttempt);
            quizAttemptDto.setNumberOfQuestionAttempted(numberOfQuestionAttempted);
            quizAttemptDtoList.add(quizAttemptDto);
        }
        return quizAttemptDtoList;
    }

    @Override
    public QuizAttemptDto updateWarningCount(Short quizId, Short userId, Integer warningCount) throws QuizNotFoundException {
        Optional<QuizAttempt> quizAttempt = quizAttemptRepo.findById(new QuizAttemptId(userId, quizId));
        if(quizAttempt.isEmpty()){
            throw new QuizNotFoundException("Quiz Attempt not found");
        }
        Double timeTaken = quizAttempt.get().getTimeTaken();
        quizAttempt.get().setWarningCount(warningCount);
        quizAttempt.get().setTimeTaken(timeTaken + calculateTimeTaken(quizAttempt.get().getLastUpdate(), LocalDateTime.now()));
        return QuizAttemptMapper.toDto(quizAttemptRepo.save(quizAttempt.get()));
    }

    @Override
    public List<Map<Integer, Integer>> getNumberOfQuizAttemptMonthWiseInYear(Short userId, Year year) {
        List<Map<Integer, Integer>> monthWiseAttempts = new ArrayList<>();
        List<Object[]> results = quizAttemptRepo.getNumberOfQuizAttemptMonthWiseInYear(userId, year);
        for (Object[] result : results) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put((Integer) result[0], ((Long) result[1]).intValue());
            monthWiseAttempts.add(map);
        }
        return monthWiseAttempts;
    }

    @Override
    public Boolean deleteQuizAttempt(Short userId) {
        List<QuizAttempt> quizAttemptList = quizAttemptRepo.findByUserId(userId);
        quizAttemptRepo.deleteAll(quizAttemptList);
        return true;
    }

    private Double calculateTimeTaken(LocalDateTime createdAt, LocalDateTime lastUpdate) {
        if (createdAt != null && lastUpdate != null) {
            Duration duration = Duration.between(createdAt, lastUpdate);
            return (double) duration.toSeconds();
        }
        return 0.0;
    }
}
