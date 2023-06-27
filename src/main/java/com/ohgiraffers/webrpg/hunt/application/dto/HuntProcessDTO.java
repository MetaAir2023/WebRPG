package com.ohgiraffers.webrpg.hunt.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class HuntProcessDTO {
    private IntegrateUserAttackDTO integrateUserAttackDTO;
    private IntegrateMonsterAttackDTO integrateMonsterAttackDTO;
    private Integer userAttackCnt;
    private Integer monsterAttackCnt;
}
