package com.ohgiraffers.webrpg.user.domain.service;

import com.ohgiraffers.webrpg.user.application.dto.UserLevelUpDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserDomainService {

    @Value("${strikingPowerConstantNumber:1}")
    private int strikingPowerConstantNumber;

    @Value("${HPConstantNumber:1}")
    private int HPConstantNumber;

    @Value("${LevelUpConstantNumber:1}")
    private int LevelUpConstantNumber;

    /**
    # 사용자의 총 공격력 계산 메소드
     기본 공격력 = 사용자의 defaultSTR + level * strikingPowerConstantNumber; 100 + 1*100 = 200 (level = 1, strikingPowerConstantNumber = 100)
     (strikingPowerConstantNumber 는 사전에 정의한 상수)
     총 공격력 : 기본 공격력 * upgradeLevel 의 제곱 200 * 4 = 800 (upgradeLevel = 2)
     만약 upgradeLevel 이 0일 경우, 기본 공격력이 총 공격력
     */
    public int calcTotalStrikingPower(int defaultSTR ,int level, int upgradeLevel) {
        int basicStrikingPower = defaultSTR + level * strikingPowerConstantNumber;
        return upgradeLevel == 0 ? basicStrikingPower : basicStrikingPower * (int) Math.pow(upgradeLevel,2);
    }

    /**
     # 사용자의 총 체력 계산 메소드
     기본 체력 = 사용자의 defaultSTR + level * HPConstantNumber; 1000 + 1*100 = 1100
     (HPConstantNumber 는 사전에 정의한 상수)
     총 체력 : 기본 체력 * upgradeLevel 의 체력 1100 * 4 = 4400
     만약 upgradeLevel 이 0일 경우, 기본 체력이 총 체력
     */
    public int calcTotalHP(int defaultHP ,int level, int upgradeLevel) {
        int basicHP = defaultHP + level * HPConstantNumber;
        return upgradeLevel == 0 ? basicHP : basicHP * (int) Math.pow(upgradeLevel,2);
    }

    public int calcLevelUpEXP(int userLevel) {
        return userLevel * 10 * LevelUpConstantNumber;
    }

    public int calcPossessionEXP(int beforeEXP, int gainEXP) {
        return beforeEXP + gainEXP;
    }

    public UserLevelUpDTO calLevelUp(int userLevel, int userPossessionEXP) {
        int balanceEXP;
        int newLevel;
        int levelUpEXP = calcLevelUpEXP(userLevel);
        if (userPossessionEXP > levelUpEXP) {
            balanceEXP = userPossessionEXP - levelUpEXP;
            newLevel = userLevel + 1;
        } else {
            balanceEXP = userPossessionEXP;
            newLevel = userLevel;
        }
        return new UserLevelUpDTO(balanceEXP, newLevel);
    }

    public Money calcBalanceMoney(Money userMoney, int gainMoney){
        return new Money(userMoney.getValue() + gainMoney);
    }
}
