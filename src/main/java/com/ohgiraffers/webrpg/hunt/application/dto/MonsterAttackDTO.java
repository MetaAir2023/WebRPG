package com.ohgiraffers.webrpg.hunt.application.dto;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.MonsterHp;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonsterAttackDTO {
    private Monster monster;
    private MonsterHp monsterCurrentHP;

    private UserInfoDTO user;
    private int userCurrentHP;
}
