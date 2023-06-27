package com.ohgiraffers.webrpg.hunt.application.dto;


import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MonsterInfoDTO {
    private int sequence;
    private String monsterName;
    private int monsterHp;
    private int monsterSTR;
    private int monsterRewardExp;
    private int monsterRewardMoney;
    private MonsterET monsterElementalType;
}
