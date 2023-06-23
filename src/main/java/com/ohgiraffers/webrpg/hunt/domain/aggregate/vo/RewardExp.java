package com.ohgiraffers.webrpg.hunt.domain.aggregate.vo;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RewardExp {

    int value;
    public RewardExp(int value) {
        if(value < 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.value = value;
        }

    }
}
