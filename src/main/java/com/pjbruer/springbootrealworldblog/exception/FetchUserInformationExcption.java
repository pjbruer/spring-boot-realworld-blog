package com.pjbruer.springbootrealworldblog.exception;

public class FetchUserInformationExcption extends RuntimeException {
    public FetchUserInformationExcption(final String msg) {
        super(msg);
    }
}
