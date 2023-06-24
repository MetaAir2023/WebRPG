package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.MonsterAttackDTO;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAttackService;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("MonsterAttackController")
public class MonsterAttackController {
    private MonsterAttackService monsterAttackService;
    private InfraRepository infraRepository;

    @Autowired
    private MonsterAttackController(MonsterAttackService monsterAttackService, InfraRepository infraRepository){
        this.monsterAttackService = monsterAttackService;
        this.infraRepository = infraRepository;
    }

    public MonsterAttackDTO initMonsterAttackToUser(Monster monster, UserInfoDTO user){
        return monsterAttackService.initMonsterAttackDTO(monster, user);
    }
}
