package com.ohgiraffers.webrpg.user.domain.aggregate.vo;

import com.ohgiraffers.webrpg.user.domain.exception.MoneyNegativeException;

public class Money {
    private int value;

    public Money() {
    }

    public Money(int value) {

        if(value < 0) {
            throw new MoneyNegativeException("돈은 음수일 수 없습니다.");
        }

        this.value = value;
    }

    public int getValue() {

        return this.value;
    }
}
