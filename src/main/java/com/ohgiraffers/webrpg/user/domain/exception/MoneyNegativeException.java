package com.ohgiraffers.webrpg.user.domain.exception;

public class MoneyNegativeException extends IllegalArgumentException {

    public MoneyNegativeException(String msg) {
        super(msg);
    }
}
