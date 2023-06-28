package com.ohgiraffers.webrpg.user.domain.exception;

public class PasswordUnCorrectException extends RuntimeException {
    public PasswordUnCorrectException() {
        super();
    }

    public PasswordUnCorrectException(String msg) {
        super(msg);
    }
}
