package com.gyanpath.security.exception;

public class SamePasswordException extends Exception{
    public SamePasswordException(String msg){
        super(msg);
    }
}
