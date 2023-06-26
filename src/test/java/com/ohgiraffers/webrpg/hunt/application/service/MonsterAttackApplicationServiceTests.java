package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import org.junit.jupiter.api.DisplayName;
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
    @Autowired
    private HuntElementalDamage huntElementalDamage;

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
    @DisplayName("몬스터가 유저를 단 한번 공격하는 속성 공격 Test - 몬스터의 속성이 열세일 경우, 약화된 공격")
    public void testAttackToUser(){
        MonsterDTO monster = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(2));
        User user = userApplicationService.getUserBySequence(1);
        UserInfoDTO userInfo = userApplicationService.getInfo(user);
        int sequence = 1;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        MonsterET monsterET = MonsterET.WATER;
        ElementalType userET = ElementalType.GRASS;
        getElementalDTO.setMonET(monsterET);
        getElementalDTO.setUserET(userET);
        MonsterAttackDTO monsterAttack = monsterAttackApplicationService.initMonsterAttackDTO(monster, userInfo);
        monsterAttack = monsterAttackApplicationService.attackToUser(monsterAttack, sequence, getElementalDTO);
        assertEquals(1010, monsterAttack.getUserCurrentHP());
    }

    @Test
    @DisplayName("몬스터가 유저를 단 한번 공격하는 속성 공격 Test - 몬스터의 속성이 동등할 경우, 일반 공격")
    public void testAttackToUser2(){
        MonsterDTO monster = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(2));
        User user = userApplicationService.getUserBySequence(1);
        UserInfoDTO userInfo = userApplicationService.getInfo(user);
        int sequence = 1;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        MonsterET monsterET = MonsterET.WATER;
        ElementalType userET = ElementalType.WATER;
        getElementalDTO.setMonET(monsterET);
        getElementalDTO.setUserET(userET);
        MonsterAttackDTO monsterAttack = monsterAttackApplicationService.initMonsterAttackDTO(monster, userInfo);
        monsterAttack = monsterAttackApplicationService.attackToUser(monsterAttack, sequence, getElementalDTO);
        assertEquals(1000, monsterAttack.getUserCurrentHP());
    }

    @Test
    @DisplayName("몬스터가 유저를 단 한번 공격하는 속성 공격 Test - 몬스터의 속성이 우세할 경우, 강화된 공격")
    public void testAttackToUser3(){
        MonsterDTO monster = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(2));
        User user = userApplicationService.getUserBySequence(1);
        UserInfoDTO userInfo = userApplicationService.getInfo(user);
        int sequence = 1;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        MonsterET monsterET = MonsterET.WATER;
        ElementalType userET = ElementalType.FIRE;
        getElementalDTO.setMonET(monsterET);
        getElementalDTO.setUserET(userET);
        MonsterAttackDTO monsterAttack = monsterAttackApplicationService.initMonsterAttackDTO(monster, userInfo);
        monsterAttack = monsterAttackApplicationService.attackToUser(monsterAttack, sequence, getElementalDTO);
        assertEquals(990, monsterAttack.getUserCurrentHP());
    }

    @Test
    @DisplayName("몬스터가 유저를 3번 공격했을 경우, 그 다음 공격 패턴은 -두번 공격-")
    public void testMonsterPatternDoubleAttack(){
        MonsterDTO monsterDTO = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(2));
        monsterDTO.setMonsterPower(100);
        User user = userApplicationService.getUserBySequence(1);
        UserInfoDTO userInfo = userApplicationService.getInfo(user);
        MonsterAttackDTO monsterAttackDTO = monsterAttackApplicationService.initMonsterAttackDTO(monsterDTO, userInfo);
        MonsterPatternDTO monsterPatternDTO = monsterAttackApplicationService.initMonsterPatternDTO();
        monsterPatternDTO.setAttackCnt(3);
        int sequence = 1;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        MonsterET monsterET = MonsterET.WATER;
        ElementalType userET = ElementalType.FIRE;
        getElementalDTO.setMonET(monsterET);
        getElementalDTO.setUserET(userET);

        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = monsterAttackApplicationService.initIntegrateMonsterAttackDTO(monsterAttackDTO, monsterPatternDTO);
        integrateMonsterAttackDTO = monsterAttackApplicationService.attackPattern(integrateMonsterAttackDTO, sequence, getElementalDTO);
        assertEquals(880, integrateMonsterAttackDTO.getMonsterAttackDTO().getUserCurrentHP());
    }

//    ***** 참고용 *****
//    속성 공격 추가 이전 테스트 코드
//    @Test
//    @DisplayName("몬스터가 유저를 3번 공격했을 경우, 그 다음 공격 패턴은 -두번 공격- 공격 카운트 덧셈이 올바르게 작동하는지 확인")
//    public void testMonsterPatternDoubleAttackCheckAttackCnt(){
//        MonsterDTO monsterDTO = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(2));
//        monsterDTO.setMonsterPower(100);
//        User user = userApplicationService.getUserBySequence(1);
//        UserInfoDTO userInfo = userApplicationService.getInfo(user);
//        MonsterAttackDTO monsterAttackDTO = monsterAttackApplicationService.initMonsterAttackDTO(monsterDTO, userInfo);
//        MonsterPatternDTO monsterPatternDTO = monsterAttackApplicationService.initMonsterPatternDTO();
//        monsterPatternDTO.setAttackCnt(3);
//        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = monsterAttackApplicationService.initIntegrateMonsterAttackDTO(monsterAttackDTO, monsterPatternDTO);
//        integrateMonsterAttackDTO = monsterAttackApplicationService.attackPattern(integrateMonsterAttackDTO);
//        assertEquals(5, integrateMonsterAttackDTO.getMonsterPatternDTO().getAttackCnt());
//    }
//
//    @Test
//    @DisplayName("최대 HP가 100인 몬스터가 체력 30%이하로 깎였을 경우에, 회복 패턴 실시 (단, 회복 패턴은 한번만 실행된다.)")
//    public void testMonsterPatternHeal(){
//        MonsterDTO monsterDTO = monsterAttackApplicationService.getInfo(monsterInfraRepository.findMonsterBySequence(2));
//        monsterDTO.setMonsterPower(100);
//        User user = userApplicationService.getUserBySequence(1);
//        UserInfoDTO userInfo = userApplicationService.getInfo(user);
//        MonsterAttackDTO monsterAttackDTO = monsterAttackApplicationService.initMonsterAttackDTO(monsterDTO, userInfo);
//        MonsterPatternDTO monsterPatternDTO = monsterAttackApplicationService.initMonsterPatternDTO();
//        monsterPatternDTO.setAttackCnt(3);
//        monsterAttackDTO.setMonsterCurrentHP(29);
//        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = monsterAttackApplicationService.initIntegrateMonsterAttackDTO(monsterAttackDTO, monsterPatternDTO);
//        integrateMonsterAttackDTO = monsterAttackApplicationService.attackPattern(integrateMonsterAttackDTO);
//        assertEquals(39, integrateMonsterAttackDTO.getMonsterAttackDTO().getMonsterCurrentHP());
//    }
//    ***** 속성 공격 추가 이전 테스트 코드 *****

}
