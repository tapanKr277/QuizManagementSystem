package com.gyanpath.quiz.exception;

public class QuizAlreadyCompletedException extends Exception{
    public QuizAlreadyCompletedException(String msg){
        super(msg);
    }
}
