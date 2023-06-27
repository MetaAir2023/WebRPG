package com.ohgiraffers.webrpg.user.infra.exception;

public class UserExistException extends RuntimeException {

    public UserExistException() {
        super();
    }

    public UserExistException(String msg) {
        super(msg);
    }
}
