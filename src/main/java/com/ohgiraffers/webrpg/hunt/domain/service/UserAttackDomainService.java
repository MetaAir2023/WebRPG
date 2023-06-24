package com.ohgiraffers.webrpg.hunt.domain.service;

import org.springframework.stereotype.Service;

@Service
public class UserAttackDomainService {

    public int attack(int hp, int power) {

        return hp - power;
    }

    public int hpCalc(int hp, int maxHp) {

        int hpPercent = (int)((hp / (double) maxHp) * 100);

        return hpPercent;
    }
}
