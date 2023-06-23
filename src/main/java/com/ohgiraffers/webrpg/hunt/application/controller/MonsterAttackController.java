package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.MonsterAttackDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterDTO;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAttackApplicationService;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("MonsterAttackController")
public class MonsterAttackController {
    private MonsterAttackApplicationService monsterAttackApplicationService;
    private InfraRepository infraRepository;

    @Autowired
    private MonsterAttackController(MonsterAttackApplicationService monsterAttackApplicationService, InfraRepository infraRepository){
        this.monsterAttackApplicationService = monsterAttackApplicationService;
        this.infraRepository = infraRepository;
    }

    public MonsterAttackDTO initMonsterAttackToUser(MonsterDTO monster, UserInfoDTO user){
        return monsterAttackApplicationService.initMonsterAttackDTO(monster, user);
    }
}
