package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterAttackDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
public class MonsterAttackApplicationServiceTests {
    @Autowired
    private MonsterAttackApplicationService monsterAttackApplicationService;
    @Autowired
    private UserApplicationService userApplicationService;
    @Autowired
    private InfraRepository monsterInfraRepository;

    @Test
    public void monsterAttackApplicationServiceTest() {
        assertNotNull(monsterAttackApplicationService);
        assertNotNull(userApplicationService);
    }

    @Test
    public void testMonsterGetInfo() {
        int sequence = 1;
        MonsterDTO monster = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(sequence));
        assertEquals("네파리안", monster.getMonsterName());
    }

    @Test
    public void testInitMonsterAttackDTO(){
        MonsterDTO monster = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(1));
        User user = userApplicationService.getUserBySequence(1);
        UserInfoDTO userInfo = userApplicationService.getInfo(user);
        MonsterAttackDTO monsterAttack = monsterAttackApplicationService.initMonsterAttackDTO(monster, userInfo);
        assertEquals("네파리안", monsterAttack.getMonster().getMonsterName());
        assertEquals("소드마스터", monsterAttack.getUser().getName());
    }

    @Test
    public void testAttackToUser(){
        MonsterDTO monster = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(1));
        User user = userApplicationService.getUserBySequence(1);
        UserInfoDTO userInfo = userApplicationService.getInfo(user);
        MonsterAttackDTO monsterAttack = monsterAttackApplicationService.initMonsterAttackDTO(monster, userInfo);
        monsterAttack = monsterAttackApplicationService.attackToUser(monsterAttack);
        assertEquals(1099, monsterAttack.getUserCurrentHP());
    }

}
