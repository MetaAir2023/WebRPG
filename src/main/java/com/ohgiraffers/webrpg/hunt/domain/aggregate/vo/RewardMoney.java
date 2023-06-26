package com.ohgiraffers.webrpg.hunt.domain.aggregate.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RewardMoney {
    int value;
    public RewardMoney(int value) {
        if(value < 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.value = value;
        }

    }
    public int getValue() {
        return value;
    }
}
