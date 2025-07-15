package com.gyanpath.security.exception;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(String msg){
        super(msg);
    }
}
