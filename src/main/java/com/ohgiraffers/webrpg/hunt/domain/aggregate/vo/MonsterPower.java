package com.ohgiraffers.webrpg.hunt.domain.aggregate.vo;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class MonsterPower {


    int value;
    public MonsterPower(int value) {
        if(value <= 0) {
            this.value = 0;
        }
        else {
            this.value = value;
        }

    }

    public int getValue() {
        return value;
    }
}
