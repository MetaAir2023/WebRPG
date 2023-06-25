package com.ohgiraffers.webrpg.hunt.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IntegrateMonsterAttackDTO {
    MonsterAttackDTO monsterAttackDTO;
    MonsterPatternDTO monsterPatternDTO;
}
