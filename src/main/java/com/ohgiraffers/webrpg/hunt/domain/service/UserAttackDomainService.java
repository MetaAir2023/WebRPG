package com.ohgiraffers.webrpg.hunt.domain.service;

import org.springframework.stereotype.Service;

@Service
public class UserAttackDomainService {

    public int attack(int hp, int power) {

        return hp - power;
    }


}
