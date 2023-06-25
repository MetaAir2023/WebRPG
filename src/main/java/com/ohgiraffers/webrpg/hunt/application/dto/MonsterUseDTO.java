package com.ohgiraffers.webrpg.hunt.application.dto;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.MonsterHp;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.MonsterPower;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.RewardExp;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.RewardMoney;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MonsterUseDTO {

    private MonsterHp monsterHp;
    private MonsterPower monsterPower;
    private RewardExp rewardExp;
    private RewardMoney rewardMoney;
    private MonsterET monsterET;
}
