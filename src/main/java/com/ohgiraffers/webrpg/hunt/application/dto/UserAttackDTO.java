package com.ohgiraffers.webrpg.hunt.application.dto;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.MonsterHp;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserLevelUpDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserStatDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserUpgradeStatDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserAttackDTO {

    private UserInfoDTO userInfoDTO;
    private UserLevelUpDTO userLevelUpDTO;
    private UserStatDTO userStatDTO;
    private UserUpgradeStatDTO userUpgradeStatDTO;
    private Monster monster;
    private MonsterHp monsterCurrentHP;
    private int userCurrentHP;


}
