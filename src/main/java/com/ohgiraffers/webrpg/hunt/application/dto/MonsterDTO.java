package com.ohgiraffers.webrpg.hunt.application.dto;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonsterDTO {
    private int monsterSequence;
    private String monsterName;
    private int monsterHP;
    private int monsterPower;
    private int monsterRewardEXP;
    private int monsterRewardMoney;
    private MonsterET elementalType;
}
