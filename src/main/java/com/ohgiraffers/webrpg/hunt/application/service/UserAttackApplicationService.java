package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.domain.service.UserAttackDomainService;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.infra.repository.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.RecursiveTask;

@Service
public class UserAttackApplicationService {

    private final UserAttackDomainService userAttackDomainService;

    private final InMemoryUserRepository inMemoryUserRepository;

    @Autowired
    public UserAttackApplicationService(UserAttackDomainService userAttackDomainService
                                      , InMemoryUserRepository inMemoryUserRepository){
        this.userAttackDomainService = userAttackDomainService;
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    public int getHpPercent(int curHp, int maxHp) {

        return userAttackDomainService.hpCalc(curHp, maxHp);
    }

    public int huntMonster(boolean flag, int monsterHp, int monsterATK, int userHp, int userATK) {

        return userAttackDomainService.attack(monsterHp, userATK);
    }

}
