package com.ohgiraffers.webrpg.user.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserDomainService {

    @Value("${strikingPowerConstantNumber:10}")
    private int strikingPowerConstantNumber;

    @Value("${HPConstantNumber:10}")
    private int HPConstantNumber;

    /**
    # 사용자의 총 공격력 계산 메소드
     기본 공격력 = 사용자의 defaultSTR + level * strikingPowerConstantNumber;
     (strikingPowerConstantNumber 는 사전에 정의한 상수)
     총 공격력 : 기본 공격력 * upgradeLevel 의 제곱
     만약 upgradeLevel 이 0일 경우, 기본 공격력이 총 공격력
     */
    public int calcTotalStrikingPower(int defaultSTR ,int level, int upgradeLevel) {
        int basicStrikingPower = defaultSTR + level * strikingPowerConstantNumber;
        return upgradeLevel == 0 ? basicStrikingPower : basicStrikingPower * (int) Math.pow(upgradeLevel,2);
    }

    /**
     # 사용자의 총 체력 계산 메소드
     기본 체력 = 사용자의 defaultSTR + level * HPConstantNumber;
     (HPConstantNumber 는 사전에 정의한 상수)
     총 체력 : 기본 체력 * upgradeLevel 의 체력
     만약 upgradeLevel 이 0일 경우, 기본 체력이 총 체력
     */
    public int calcTotalHP(int defaultHP ,int level, int upgradeLevel) {
        int basicHP = defaultHP + level * HPConstantNumber;
        return upgradeLevel == 0 ? basicHP : basicHP * (int) Math.pow(upgradeLevel,2);
    }
}
