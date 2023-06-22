package com.ohgiraffers.webrpg.hunt.domain.service;

public class AbilityCalc {


    public int hpPercentCalc(int hp, int maxHp){
        int hpPer = (int)(hp / (double) maxHp) * 100;
        return hpPer;
    }





}
