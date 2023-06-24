package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.MonsterUseDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.UserAttackDTO;
import com.ohgiraffers.webrpg.hunt.application.service.UserAttackApplicationService;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userattck")
public class UserAttackController {
    /* 유저가 몬스터를 공격하는 구문을 최종 작성한다. */
    private UserAttackApplicationService userAttackApplicationService;


    @Autowired
    private UserAttackController(UserAttackApplicationService userAttackApplicationService) {
        this.userAttackApplicationService = userAttackApplicationService;
    }

//    public MonsterUseDTO attackToMonster(MonsterUseDTO monsterUseDTO, int userATK){
//
//        int monsterHp = userAttackApplicationService.huntMonster(true, monsterUseDTO.getMonsterHp(), monsterUseDTO.getMonsterPower(), 0, userATK);
//        monsterUseDTO.setMonsterHp(monsterHp);
//        return monsterUseDTO;
//    }
}

