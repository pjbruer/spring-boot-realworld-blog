package com.pjbruer.springbootrealworldblog.exception;

public class UserInfoNotFoundException extends RuntimeException{
    public UserInfoNotFoundException(String msg) {super(msg);}
}