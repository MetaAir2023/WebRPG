package com.ohgiraffers.webrpg.hunt.domain.aggregate.entity;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.MonsterHp;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.MonsterPower;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.RewardExp;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.RewardMoney;

public class Monster {
    private int sequence;
    private String monsterName;
    private MonsterHp monsterHp;
    private MonsterPower monsterPower;
    private RewardExp rewardExp;
    private RewardMoney rewardMoney;
    private MonsterET monsterElementalType;


    public Monster(int sequence, String monsterName, int monsterHp, int monsterPower, int rewardExp, int rewardMoney, MonsterET monsterElementalType) {
        this.sequence = sequence;
        this.monsterName = monsterName;
        this.monsterHp = new MonsterHp(monsterHp);
        this.monsterPower = new MonsterPower(monsterPower);
        this.rewardExp = new RewardExp(rewardExp);
        this.rewardMoney = new RewardMoney(rewardMoney);
        this.monsterElementalType = monsterElementalType;
    }


    public int getSequence() {
        return sequence;
    }


    public String getMonsterName() {
        return monsterName;
    }

    public MonsterHp getMonsterHp() {
        return monsterHp;
    }

    public MonsterPower getMonsterPower() {
        return monsterPower;
    }

    public RewardExp getRewardExp() {
        return rewardExp;
    }

    public RewardMoney getRewardMoney() {
        return rewardMoney;
    }
    public MonsterET getMonElement(){
        return monsterElementalType;
    }
}
