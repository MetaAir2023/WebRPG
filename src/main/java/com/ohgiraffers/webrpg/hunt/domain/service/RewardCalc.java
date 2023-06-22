package com.ohgiraffers.webrpg.hunt.domain.service;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.RewardMoney;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;

public class RewardCalc {

    public RewardMoney earnMoney(RewardMoney rewardMoney, Money userMoney){
       int value = Integer.parseInt(rewardMoney.toString()) + Integer.parseInt(userMoney.toString());
       RewardMoney reward = new RewardMoney(value);

       return reward;
    }


}
