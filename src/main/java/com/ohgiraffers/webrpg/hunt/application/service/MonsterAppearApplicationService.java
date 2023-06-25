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
        Monster monster = monsterAppearDomainService.randomMapMonster(mapNum);
        return new RandomMonsterDTO(monster.getSequence(),monster.getMonsterName(),monster.getMonsterHp(),
                monster.getMonsterPower(),monster.getRewardExp(),monster.getRewardMoney(),monster.getMonElement());
    }





}
