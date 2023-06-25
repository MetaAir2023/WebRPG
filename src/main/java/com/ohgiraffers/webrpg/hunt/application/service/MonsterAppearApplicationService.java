package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.RandomMonsterDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.service.MonsterAppearDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MonsterAppearApplicationService {


    private final MonsterAppearDomainService monsterAppearDomainService;

    @Autowired
    public MonsterAppearApplicationService(MonsterAppearDomainService monsterAppearDomainService){
        this.monsterAppearDomainService = monsterAppearDomainService;
    }


    public RandomMonsterDTO randomMonster(int mapNum){
        RandomMonsterDTO randomMon = new RandomMonsterDTO();
        Monster monster = monsterAppearDomainService.randomMapMonster(mapNum);
        randomMon.setMonsterName(monster.getMonsterName());
        randomMon.setMonsterHp(monster.getMonsterHp());
        randomMon.setMonsterPower(monster.getMonsterPower());
        randomMon.setRewardExp(monster.getRewardExp());
        randomMon.setRewardMoney(monster.getRewardMoney());
        randomMon.setMonElement(monster.getMonElement());
        return randomMon;
    }





}
